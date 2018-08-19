package coding.challenge.controller;

import coding.challenge.service.TelephoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TelephoneController {

    @Autowired
    TelephoneService telephoneService;


    @GetMapping("/getPhoneNumbers")
    public ResponseEntity getPhoneNumbers() {
        try {
            return new ResponseEntity(telephoneService.getAllTelephoneNumbers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/getPhoneNumberByName")
    public ResponseEntity getPhoneNumberByName(@RequestBody String name) {
        try {
            return new ResponseEntity(telephoneService.getPhoneNumberByName(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping(value = "/activatePhoneNumber")
    public ResponseEntity activatePhoneNumber(@RequestBody String telephone) {
        try {
            Boolean success = telephoneService.activateTelephoneNumber(telephone);
            if (success) {
                return new ResponseEntity("number activated", HttpStatus.OK);
            } else {
                return new ResponseEntity("number already activated", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }


    }
}