package org.example.server.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.server.enums.NewType;
import org.example.server.po.New;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NewVO {

    private Integer id;

    private Date createTime;

    private Integer authorId;

    private NewType type;

    private String title;

    private String content;

    private List<Integer> imgurlList;

    public New toPO() {
        New newPO = new New();
        newPO.setId(this.id);
        newPO.setType(this.type);
        newPO.setCreateTime(this.createTime);
        newPO.setAuthorId(this.authorId);
        newPO.setTitle(this.title);
        newPO.setContent(this.content);
        newPO.setImgurlList(this.imgurlList);
        return newPO;
    }
}
