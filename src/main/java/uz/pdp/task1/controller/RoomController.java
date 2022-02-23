package uz.pdp.task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.task1.exeptions.PageSizeException;
import uz.pdp.task1.payload.ApiResponse;
import uz.pdp.task1.payload.RoomDto;
import uz.pdp.task1.servise.RoomService;
import uz.pdp.task1.utils.AppConstants;

@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired
    RoomService roomService;

    @PostMapping("/saveOrEdit")
    public HttpEntity<?> saveOrEdit(@RequestBody RoomDto roomDto) {

        ApiResponse apiResponse = roomService.saveOrEdit(roomDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);


    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> delete(@PathVariable Long id) {

        ApiResponse apiResponse = roomService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @GetMapping("/getAll")// pageble shakda hotel id orqali olib kelish agar all=false bo'lsa id oqali kelish shart emas
    public HttpEntity<?> getAll(
            @RequestParam(required = false) Long hotelId,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER,required = false) Integer page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE,required = false) Integer size,
            @RequestParam boolean all
    ) throws PageSizeException {
        ApiResponse apiResponse = roomService.getAll(hotelId,page,size,all);

        return ResponseEntity.ok(apiResponse);
    }
}

