package com.example.nationflags.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.nationflags.model.Flag;
import com.example.nationflags.repository.FlagRepository;

@RestController
@RequestMapping("/flags")
public class FlagController {

    @Autowired
    private FlagRepository flagRepo;
    
    @GetMapping("/getall")
    public ResponseEntity<List<Flag>> getAllFlags() {
        List<Flag> flags = (List<Flag>)flagRepo.findAll();
        return new ResponseEntity<Flag>(flags, HttpStatus.OK);
    }

    @GetMapping("/getflag/{nation}")
    public ResponseEntity<Flag> getFlagByNation(@PathVariable("nation") String nation) {
        Flag flag = flagRepo.findById(nation).get();
        return new ResponseEntity<Flag>(flag, HttpStatus.OK);
    }

}
