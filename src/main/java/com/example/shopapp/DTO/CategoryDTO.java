package com.example.shopapp.DTO;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data // toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
//    private long id; id sẽ sinh ở csdl
    @NotEmpty(message = "Category name can not be empty")
    private String name;
}
