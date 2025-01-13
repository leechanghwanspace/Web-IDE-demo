package com.groom.WebIDE.docker.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class DockerCommandService {

    public String executeDockerCommand(String fileName, String filePath, String language) throws IOException, InterruptedException {
        String dockerCommand;

        switch (language.toLowerCase()) {
            case "c":
                dockerCommand = String.format(
                        "docker run --rm -v \"%s:/usr/src/app\" test-runner bash -c \"gcc /usr/src/app/%s -o /usr/src/app/%s_exe && /usr/src/app/%s_exe\"",
                        filePath, fileName, fileName, fileName
                );
                break;

            case "c++":
            case "cpp": // C++은 두 가지 언어 이름을 지원
                dockerCommand = String.format(
                        "docker run --rm -v \"%s:/usr/src/app\" test-runner bash -c \"g++ /usr/src/app/%s -o /usr/src/app/%s_exe && /usr/src/app/%s_exe\"",
                        filePath, fileName, fileName, fileName
                );
                break;

            case "java":
                dockerCommand = String.format(
                        "docker run --rm -v \"%s:/usr/src/app\" test-runner bash -c \"javac /usr/src/app/%s && java -cp /usr/src/app %s\"",
                        filePath, fileName, fileName.replace(".java", "")
                );
                break;

            case "python":
                dockerCommand = String.format(
                        "docker run --rm -v \"%s:/usr/src/app\" test-runner bash -c \"python3 /usr/src/app/%s\"",
                        filePath, fileName
                );
                break;

            case "javascript":
                dockerCommand = String.format(
                        "docker run --rm -v \"%s:/usr/src/app\" test-runner bash -c \"node /usr/src/app/%s\"",
                        filePath, fileName
                );
                break;

            default:
                throw new IllegalArgumentException("Unsupported language: " + language);
        }

        Process process = Runtime.getRuntime().exec(dockerCommand);

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder output = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line).append("\n");
        }

        process.waitFor();

        int exitCode = process.exitValue();
        if (exitCode != 0) {
            throw new RuntimeException("Error during code execution: Exit code " + exitCode);
        }

        return output.toString().trim();
    }
}
