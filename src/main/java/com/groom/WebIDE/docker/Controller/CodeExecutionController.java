package com.groom.WebIDE.docker.Controller;

import com.groom.WebIDE.docker.service.DockerCommandService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/execute")
public class CodeExecutionController {

    private final DockerCommandService dockerCommandService;

    public CodeExecutionController(DockerCommandService dockerCommandService) {
        this.dockerCommandService = dockerCommandService;
    }

    @PostMapping
    public String executeCode(
            @RequestParam("fileName") String fileName,
            @RequestParam("filePath") String filePath,
            @RequestParam("language") String language // 언어 선택
    ) {
        try {
            return dockerCommandService.executeDockerCommand(fileName, filePath, language);
        } catch (Exception e) {
            return "Code execution failed! Error: " + e.getMessage();
        }
    }
}
