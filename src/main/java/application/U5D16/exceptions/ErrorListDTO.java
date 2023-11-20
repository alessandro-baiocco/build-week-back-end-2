package application.U5D16.exceptions;

import java.util.Date;
import java.util.List;

public record ErrorListDTO(String message, Date timestamp, List<String> errorsList) {
}
