package XMLAPITests;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "objects")
public class UserData {

    @JacksonXmlProperty(isAttribute = true, localName = "type")
    private String type;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "object")
    private List<ObjectData> objects;

    @Data
    public static class ObjectData {

        @JacksonXmlProperty(localName = "id")
        private IdWrapper id;

        @JacksonXmlProperty(localName = "name")
        private String name;

        @JacksonXmlProperty(localName = "email")
        private String email;

        @JacksonXmlProperty(localName = "gender")
        private String gender;

        @JacksonXmlProperty(localName = "status")
        private String status;

        @Data
        public static class IdWrapper {
            @JacksonXmlText
            private int value; //This holds the integer value of the id

            @JacksonXmlProperty(isAttribute = true, localName = "type")
            private String type; //This holds the type attribute of id
        }
    }
}
