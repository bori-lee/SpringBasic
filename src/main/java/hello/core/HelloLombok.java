package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    //롬복이 게터, 세터를 다 만들어줌..
    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("aaa");

        System.out.println("helloLombok = " + helloLombok);
        
    }
}
