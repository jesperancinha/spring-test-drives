package org.jesperancinha.titletextadder.api.pojo;

public class Response {

	private ResponseStatus responseStatus;

	public Response(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public void setResponseStatus(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}
}
