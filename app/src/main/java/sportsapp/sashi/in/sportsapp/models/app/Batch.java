package sportsapp.sashi.in.sportsapp.models.app;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "coach_batches")
public class Batch extends Model{

    private String message;

    @Column(name = "batch_name")
    public String batchName;

    @Column(name = "batch_id")
    public String batchId;

    public BatchFooter batchFooter;

    public Batch() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public BatchFooter getBatchFooter() {
        return batchFooter;
    }

    public void setBatchFooter(BatchFooter batchFooter) {
        this.batchFooter = batchFooter;
    }
}
