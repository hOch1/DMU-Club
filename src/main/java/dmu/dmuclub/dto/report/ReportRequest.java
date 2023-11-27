package dmu.dmuclub.dto.report;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportRequest {

    private String title;
    private String content;
    private int member_id;

    @Builder
    public ReportRequest(String title, String content, int member_id) {
        this.title = title;
        this.content = content;
        this.member_id = member_id;
    }
}
