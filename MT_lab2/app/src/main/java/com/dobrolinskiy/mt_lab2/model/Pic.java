package com.dobrolinskiy.mt_lab2.model;

import com.fasterxml.jackson.annotation.*;
import java.util.List;

public class Pic {
    private long count;
    private List<Entry> entries;

    @JsonProperty("count")
    public long getCount() { return count; }
    @JsonProperty("count")
    public void setCount(long value) { this.count = value; }

    @JsonProperty("entries")
    public List<Entry> getEntries() { return entries; }
    @JsonProperty("entries")
    public void setEntries(List<Entry> value) { this.entries = value; }
}
