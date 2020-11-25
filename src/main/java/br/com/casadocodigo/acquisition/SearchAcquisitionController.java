package br.com.casadocodigo.acquisition;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

//Intrinsic cognitive load: 4
@RestController
@RequiredArgsConstructor
@RequestMapping("/acquisition")
public class SearchAcquisitionController {

    //1
    private final AcquisitionRepository acquisitionRepository;

    @GetMapping("/{id}/detail")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> detail(@PathVariable("id") Long id) {
        return acquisitionRepository
                .findDetailById(id)
                .map(AcquisitionDetailProjection::toMap) /* 1 */
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)); /* 1 */
    }
}
