package POJO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponseBody {
    String id;
    PostResponseBodyInnerObject innerObject;

    PostResponseBody(){
    }
}
