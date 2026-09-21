package com.nishant.opportrack.dto;

import java.util.List;

public class AdzunaResponse {
    private List<AdzunaJob> results;

    public List<AdzunaJob> getResults() { return results; }
    public void setResults(List<AdzunaJob> results) { this.results = results; }
}