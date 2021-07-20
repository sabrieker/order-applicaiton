package com.boom.dto.response;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.slf4j.MDC;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Schema
@Data @Builder
public class BaseApiResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@Schema(title  = "Unique identifier of this response, should be the same if there is a related input request")
	@NotNull
	private String id;

	@NotNull
	@Schema(title = "When this response is generated in epoch milliseconds")
	@Builder.Default
	private Long timestamp = System.currentTimeMillis();

	private boolean success = true;

	@Schema(title = "Response code defining the business case or error of this response")
	private String responseCode;

	@Schema(title = "Default description for the response code")
	private String responseMessage;

	@Schema(title = "Optional detail of the fault if the response is not success")
	private String fault;

	@Schema(title = "Correlation id of this response to track it faster on ELK")
	private String correlationId = MDC.get("CID");

}
