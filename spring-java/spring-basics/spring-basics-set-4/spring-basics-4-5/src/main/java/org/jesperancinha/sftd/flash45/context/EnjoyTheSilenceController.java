package org.jesperancinha.sftd.flash45.context;

import jakarta.servlet.http.HttpServletResponse;
import org.jesperancinha.console.consolerizer.html.HtmlComposer;
import org.jesperancinha.console.consolerizer.html.HtmlPWriter;
import org.jesperancinha.console.consolerizer.html.HtmlizerLinks;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class EnjoyTheSilenceController {

    @GetMapping(value = "/",
            produces = MediaType.APPLICATION_XML_VALUE)
    public void home(final HttpServletResponse httpServletResponse) throws IOException {
        final var writer = httpServletResponse.getWriter();
        writer.println("<html>");
        final HtmlPWriter htmlPWriter = new HtmlPWriter(writer);
        HtmlComposer.out(" ")
                .green("Enjoy The Silence</br>")
                .newLine()
                .writeToHtml(htmlPWriter);
        htmlPWriter.printRawPrivateText(HtmlizerLinks.getYouTubeFrame("aGSKrC7dGcY"));
        HtmlComposer.out(" ")
                .newLine()
                .magenta("</br>WebApplicationInitializer won't work be cause it has been disabled as described in</br")
                .newLine()
                .blue("\nhttps://github.com/spring-projects/spring-boot/issues/522")
                .magenta(".</br>")
                .newLine()
                .green("This is the reason why we switch to ServletContextInitializer in this example.</br>")
                .writeToHtml(htmlPWriter);
        writer.println("</html>");
    }
}