package com.itba.hci.smarthome.service.payload;

import java.util.List;

public class ExecuteRoutineResponse {
    private List<Boolean> result;

    public ExecuteRoutineResponse(){

    }

    public ExecuteRoutineResponse(List<Boolean> result) {
        this.result = result;
    }

    public List<Boolean> getResult() {
        return result;
    }

    public void setResult(List<Boolean> result) {
        this.result = result;
    }
}
