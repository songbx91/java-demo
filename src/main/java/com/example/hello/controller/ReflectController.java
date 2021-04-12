package com.example.hello.controller;

import com.example.hello.model.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

@RestController
@RequestMapping("/reflect")
public class ReflectController {

    @GetMapping("/getConstructor")
    public void getConstructor() {
        Person person = new Person();
        Class clz = person.getClass();
        Constructor[] constructors = clz.getDeclaredConstructors();
        for (int i = 0; i < constructors.length; i++) {
            System.out.print(Modifier.toString(constructors[i].getModifiers()) + "参数: ");
            Class[] parametertypes = constructors[i].getParameterTypes();
            for (int j = 0; j < parametertypes.length; j++) {
                System.out.print(parametertypes[j].getName() + " ");
            }
            System.out.println("");
        }
    }
}
