package POJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post_Response {
    String id;
    Post_Response_InnerObject innerObject;

    Post_Response(){
    }
}
