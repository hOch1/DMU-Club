package dmu.dmuclub.dto.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportResponse {

    private String title;
    private String content;
    private String author;
    private boolean solve;


    @Builder
    public ReportResponse(String title, String content, String author, boolean solve) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.solve = solve;
    }
}

