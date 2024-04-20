package ms.sysredcolombia.rest.modelo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotFoundException extends RuntimeException{
	private String code;
    private String message;
}
