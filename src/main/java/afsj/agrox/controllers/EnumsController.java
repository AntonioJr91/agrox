package afsj.agrox.controllers;

import afsj.agrox.enums.UnitOfMeasure;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/enums")

public class EnumsController {

   @GetMapping("/unit-of-measures")
   public List<String> unitOfMeasures() {
      return Arrays.stream(UnitOfMeasure.values())
              .map(Enum::name)
              .toList();
   }
}
