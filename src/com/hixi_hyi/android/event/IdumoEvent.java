package com.hixi_hyi.android.event;

import com.hixi_hyi.android.data.PipeData;

public class IdumoEvent {
	private PipeData pipeData;

	public IdumoEvent(PipeData p) {
		this.pipeData = p;
	}

	/**
	 * @return pipeData
	 */
	public PipeData getPipeData() {
		return pipeData;
	}

}
