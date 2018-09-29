package com.spring.business.service;

import com.spring.Annotation.Autowired;
import com.spring.Annotation.Service;

/**
 * @author fantasy
 * @date 2018/9/28
 * @time 14:37
 */

@Service
public class UserService {

    @Autowired
    private TestService testService;

    public String getUser() {
        String test = testService.test();
        String user = "my name is fantasy";
        return user + ", " + test;
    }
}
