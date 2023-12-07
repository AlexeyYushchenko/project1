package utlc.ru.project1.http.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import utlc.ru.project1.dto.shipmentstatus.ShipmentStatusReadDto;
import utlc.ru.project1.dto.shipmentstatus.ShipmentStatusCreateUpdateDto;
import utlc.ru.project1.service.ShipmentStatusService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shipmentStatuses")
public class ShipmentStatusRestController {

    private final ShipmentStatusService shipmentStatusService;

    @GetMapping()
    public ResponseEntity<List<ShipmentStatusReadDto>> getAll() {
        List<ShipmentStatusReadDto> statuses = shipmentStatusService.findAll();
        return ResponseEntity.ok(statuses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShipmentStatusReadDto> getById(@PathVariable Integer id) {
        return shipmentStatusService.findById(id)
                .map(shipmentStatusDto -> new ResponseEntity<>(shipmentStatusDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ShipmentStatusReadDto> create(@RequestBody @Validated ShipmentStatusCreateUpdateDto createUpdateDto) {
        ShipmentStatusReadDto createdStatus = shipmentStatusService.create(createUpdateDto);
        return new ResponseEntity<>(createdStatus, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShipmentStatusReadDto> update(@PathVariable("id") Integer id, @RequestBody @Validated ShipmentStatusCreateUpdateDto createUpdateDto) {
        return shipmentStatusService.update(id, createUpdateDto)
                .map(status -> new ResponseEntity<>(status, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        if (shipmentStatusService.delete(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
