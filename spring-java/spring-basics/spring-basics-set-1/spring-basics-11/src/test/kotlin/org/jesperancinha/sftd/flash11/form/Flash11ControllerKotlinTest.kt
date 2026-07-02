package org.jesperancinha.sftd.flash11.form

import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.message.BasicNameValuePair
import org.apache.http.util.EntityUtils
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.util.*

@WebMvcTest(Flash11Controller::class)
internal class Flash11ControllerKotlinTest @Autowired constructor(
    private val mockMvc: MockMvc
){

    @Test
    @Throws(Exception::class)
    fun `should test main page and go to index`() {
        val music = Music()
        music.artist = "Tracy Chapman"
        music.song = "Talkin' About A Revolution"
        mockMvc.perform(post("/"))
            .andExpect(status().isOk)
            .andExpect(view().name("index"))
            .andExpect(model().attribute("music", music))
    }

    @Test
    @Throws(Exception::class)
    fun testHandleRequestWhenMusicReceivedThenWriteCorrectPage() {
        val music = Music()
        music.artist = "Tracy Chapman"
        music.song = "Talkin' About A Revolution"
        mockMvc.perform(
            post("/req")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .content(
                    EntityUtils.toString(
                        UrlEncodedFormEntity(listOf(
                                BasicNameValuePair("artist", music.artist),
                                BasicNameValuePair("song", music.song)
                            )
                        )
                    )
                )
        )
            .andExpect(status().isOk)
            .andExpect(
                content().string(
                    """
    You did it!
    
    <p style="margin:0; font-family: monospace; color:#00aa00"><b>{music=Music{artist='Tracy Chapman', song='Talkin' About A Revolution'}, org.springframework.validation.BindingResult.music=org.springframework.validation.BeanPropertyBindingResult: 0 errors}</b></p><p style="margin:0; font-family: monospace; color:#00aa00"><b>Music{artist='Tracy Chapman', song='Talkin' About A Revolution'}</b></p><a href="/">Back</a>
    """.trimIndent()
                )
            )
    }
}