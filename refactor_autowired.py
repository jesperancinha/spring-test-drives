import os
import re

def refactor_kotlin(file_path):
    with open(file_path, 'r', encoding='utf-8') as f:
        content = f.read()

    # 1. Clean up existing @Autowired constructor parameters
    if '@Autowired constructor' in content:
        # Remove @Autowired from parameters inside any constructor
        def clean_params(match):
            prefix = match.group(1)
            params = match.group(2)
            suffix = match.group(3)
            new_params = params.replace('@Autowired', '').strip()
            # Clean up double spaces or commas if any
            new_params = re.sub(r'\s+', ' ', new_params)
            return f"{prefix}{new_params}{suffix}"

        new_content = re.sub(r'(@Autowired\s+constructor\s*\()(.*?)(\)\s*[\{])', clean_params, content, flags=re.DOTALL)
        if new_content == content:
             new_content = re.sub(r'(@Autowired\s+constructor\s*\()(.*?)(\))', clean_params, content, flags=re.DOTALL)
        
        if new_content != content:
            with open(file_path, 'w', encoding='utf-8') as f:
                f.write(new_content)
            print(f"Cleaned Kotlin constructor params: {file_path}")
            content = new_content

    # 2. Handle field injection in Kotlin
    # Find all @Autowired fields: @Autowired [lateinit] var/val name: Type
    autowired_fields = re.findall(r'@Autowired\s+(?:private\s+|internal\s+)?(?:lateinit\s+)?(?:var|val)\s+(\w+)\s*:\s*([^=\n\s\{]+)', content)

    if autowired_fields:
        print(f"Found @Autowired fields in Kotlin: {file_path}")
        
        # Build constructor params
        params = []
        for name, type_str in autowired_fields:
            params.append(f"private val {name}: {type_str.strip()}")
            # Remove the field declaration
            content = re.sub(r'@Autowired\s+(?:private\s+|internal\s+)?(?:lateinit\s+)?(?:var|val)\s+' + name + r'\s*:\s*' + re.escape(type_str) + r'.*?\n', '', content)

        constructor_snippet = " @Autowired constructor(\n    " + ",\n    ".join(params) + "\n)"
        
        # Insert into class declaration
        class_match = re.search(r'(class\s+\w+)(\s*\{|\s*\()', content)
        if class_match:
            if class_match.group(2).strip() == '{':
                content = content.replace(class_match.group(0), class_match.group(1) + constructor_snippet + " {")
            elif '@Autowired constructor' not in content:
                # Already has primary constructor but not @Autowired one
                # This is tricky, let's just append @Autowired to existing if it matches
                pass
        
        with open(file_path, 'w', encoding='utf-8') as f:
            f.write(content)
        print(f"Refactored Kotlin (moved fields to constructor): {file_path}")

def refactor_java(file_path):
    with open(file_path, 'r', encoding='utf-8') as f:
        content = f.read()

    # Avoid refactoring if there's already a constructor with @Autowired
    if '@Autowired' in content and re.search(r'@Autowired\s+(?:public|protected|private)?\s+\w+\s*\(', content):
        # But we still need to remove @Autowired from fields if they exist
        pass

    # Find @Autowired fields
    # @Autowired private Type name;
    # Use a more robust regex for Java fields
    # We need to handle potential newlines between @Autowired and the field
    field_matches = list(re.finditer(r'@Autowired\s+(?:private\s+|public\s+|protected\s+)?([\w\<\>\[\]\.]+)\s+(\w+);', content, re.DOTALL))
    
    if not field_matches:
        return

    print(f"Refactoring Java: {file_path}")
    
    # Class name
    class_match = re.search(r'(?:public\s+)?class\s+(\w+)', content)
    if not class_match:
        return
    class_name = class_match.group(1)
    
    fields_to_inject = []
    new_content = content
    for match in field_matches:
        type_name = match.group(1)
        var_name = match.group(2)
        fields_to_inject.append((type_name, var_name))
        
        # Remove @Autowired from field
        full_match = match.group(0)
        # Use regex replace to handle whitespaces properly
        new_field_decl = re.sub(r'@Autowired\s+', '', full_match)
        new_content = new_content.replace(full_match, new_field_decl)

    content = new_content

    # Add constructor
    params = [f"{t} {v}" for t, v in fields_to_inject]
    assignments = [f"this.{v} = {v};" for t, v in fields_to_inject]
    
    constructor = f"\n    @Autowired\n    public {class_name}(" + ", ".join(params) + ") {\n        " + "\n        ".join(assignments) + "\n    }\n"
    
    # Insert constructor after the last field or before the first method
    # Let's find the first @Test or the first method
    method_match = re.search(r'(?:public|private|protected|void|static)\s+\w+\s*\(', content)
    if method_match:
        pos = method_match.start()
        # Back up to before any annotations
        lines = content[:pos].splitlines()
        insert_pos = pos
        for i in range(len(lines)-1, -1, -1):
            if lines[i].strip().startswith('@'):
                insert_pos = content.find(lines[i], 0, insert_pos)
            elif lines[i].strip() == '':
                continue
            else:
                break
        content = content[:insert_pos] + constructor + content[insert_pos:]
    else:
        # Just after class opening
        class_open = content.find('{', class_match.end())
        if class_open != -1:
            pos = class_open + 1
            content = content[:pos] + constructor + content[pos:]

    with open(file_path, 'w', encoding='utf-8') as f:
        f.write(content)
    print(f"Refactored Java: {file_path}")

def main():
    for root, dirs, files in os.walk('.'):
        for file in files:
            file_path = os.path.join(root, file)
            if file.endswith('Test.kt'):
                refactor_kotlin(file_path)
            elif file.endswith('Test.java'):
                refactor_java(file_path)

if __name__ == "__main__":
    main()
