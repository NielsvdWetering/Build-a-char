package nl.itvitae.buildachar.charactertool;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;
import nl.itvitae.buildachar.ControllerRoutes;
import nl.itvitae.buildachar.tool.Tool;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(ControllerRoutes.CHARACTER_ROUTE)
@CrossOrigin(origins = {"${app-cors}"})
public class CharacterTool {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Character character;

    @ManyToOne
    private Tool tool;
}
