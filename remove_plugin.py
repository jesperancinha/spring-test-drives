import os
import re

def remove_compiler_plugin(file_path):
    with open(file_path, 'r') as f:
        content = f.read()

    # Regex to find <plugin> ... <artifactId>maven-compiler-plugin</artifactId> ... </plugin>
    # It handles nested tags by being as non-greedy as possible but including the artifactId
    pattern = re.compile(r'\s*<plugin>.*?<artifactId>maven-compiler-plugin</artifactId>.*?</plugin>', re.DOTALL)
    
    new_content = pattern.sub('', content)
    
    if new_content != content:
        with open(file_path, 'w') as f:
            f.write(new_content)
        return True
    return False

# List of files provided by find command
files = [
    "./spring-java/spring-basics/spring-basics-set-1/spring-basics-20/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-2/spring-basics-2-15/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-2/spring-basics-2-18/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-2/spring-basics-2-7/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-2/spring-basics-2-16/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-2/spring-basics-2-9/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-2/spring-basics-2-14/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-2/spring-basics-2-5/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-2/spring-basics-2-13/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-2/spring-basics-2-4/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-2/spring-basics-2-6/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-2/spring-basics-2-20/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-5/configurable-tomato-contexts/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-5/spring-basics-5-7/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-4/spring-basics-4-4/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-4/spring-basics-4-13/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-4/spring-basics-4-16/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-4/spring-basics-4-12/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-4/spring-basics-4-6/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-4/spring-basics-4-3/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-4/spring-basics-4-2/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-3/spring-basics-3-4/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-3/spring-basics-3-14/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-3/spring-basics-3-19/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-3/spring-basics-3-9/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-3/spring-basics-3-13/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-3/spring-basics-3-10/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-3/spring-basics-3-3/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-3/spring-basics-3-11/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-3/spring-basics-3-5/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-3/spring-basics-3-1/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-3/spring-basics-3-16/pom.xml",
    "./spring-java/spring-basics/spring-basics-set-3/spring-basics-3-7/pom.xml",
    "./spring-java/spring-app-legacy/spring-test-drives-webapp/pom.xml",
    "./spring-java/spring-app-legacy/spring-test-drives-spring-boot/pom.xml",
    "./spring-java/spring-apps/spring-app-1/pom.xml",
    "./spring-java/spring-apps/spring-app-2/pom.xml",
    "./spring-java/title-text-adder/title-text-adder-api/pom.xml",
    "./spring-java/title-text-adder/title-text-adder-app/pom.xml",
    "./spring-java/title-text-adder/pom.xml",
    "./spring-java/spring-action/spring-action-ioc/pom.xml",
    "./spring-java/spring-action/spring-action-data/pom.xml",
    "./spring-java/spring-topics/spring-topic-container/pom.xml",
    "./spring-java/spring-mastery/spring-mastery-1/pom.xml",
    "./spring-java/spring-mastery/spring-mastery-2/pom.xml",
    "./spring-java/spring-mastery/spring-mastery-3/pom.xml",
]

for file in files:
    abs_path = os.path.join(os.getcwd(), file)
    if os.path.exists(abs_path):
        if remove_compiler_plugin(abs_path):
            print(f"Removed from {file}")
        else:
            print(f"No changes in {file}")
    else:
        print(f"File not found: {file}")
