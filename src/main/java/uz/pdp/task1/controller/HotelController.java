package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.entity.Hotel;
import uz.pdp.task1.payload.ApiResponse;
import uz.pdp.task1.servise.HotelServise;

@RestController
@RequestMapping("/api/hotel")
public class HotelController {

    @Autowired
    HotelServise hotelServise;

    @PostMapping("/saveOrEdit")
    public HttpEntity<?>saveOrEdit(@RequestBody Hotel hotel){
        ApiResponse apiResponse = hotelServise.saveOrEdit(hotel);
        return ResponseEntity.status(apiResponse.isSuccess()?200:409).body(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Long id){
        ApiResponse apiResponse = hotelServise.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?203:409).body(apiResponse);
    }

    @GetMapping("/get/{id}")
    public HttpEntity<?>getOne(@PathVariable Long id){
        ApiResponse response = hotelServise.get(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/getAll")
    public HttpEntity<?>getAll(){
        ApiResponse response = hotelServise.getAll();
        return ResponseEntity.ok(response);
    }

}
