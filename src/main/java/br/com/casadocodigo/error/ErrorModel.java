package br.com.casadocodigo.error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorModel {
    private String timestamp;
    private int status;
    private List<String> errors;
    private String path;
}
