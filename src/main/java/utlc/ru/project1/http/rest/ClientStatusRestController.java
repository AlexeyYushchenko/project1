package utlc.ru.project1.http.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utlc.ru.project1.dto.ClientStatusReadDto;
import utlc.ru.project1.dto.ClientStatusUpdateDto;
import utlc.ru.project1.service.ClientStatusService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/clientStatuses")
public class ClientStatusRestController {

    private final ClientStatusService clientStatusService;

    // Get all client statuses
    @GetMapping()
    public ResponseEntity<List<ClientStatusReadDto>> getAll() {
        List<ClientStatusReadDto> statuses = clientStatusService.findAll();
        return ResponseEntity.ok(statuses);
    }

    // Get a single client status by ID
    @GetMapping("/{id}")
    public ResponseEntity<ClientStatusReadDto> getById(@PathVariable Integer id) {
        return clientStatusService.findById(id)
                .map(clientStatusDto -> new ResponseEntity<>(clientStatusDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientStatusReadDto> create(@RequestBody @Validated ClientStatusReadDto newClientStatus) {
        ClientStatusReadDto createdStatus = clientStatusService.create(newClientStatus);
        return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientStatusReadDto> update(@PathVariable("id") Integer id, @RequestBody @Validated ClientStatusUpdateDto updatedStatus) {
        return clientStatusService.update(id, updatedStatus)
                .map(status -> new ResponseEntity<>(status, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (clientStatusService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
