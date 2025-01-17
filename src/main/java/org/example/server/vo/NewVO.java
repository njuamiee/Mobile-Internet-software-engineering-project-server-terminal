package org.example.server.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.server.enums.NewType;
import org.example.server.po.New;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NewVO {

    private Integer id;

    private Date createTime;

    private Integer authorId;

    private String type;

    private Integer likeCount;

    private String title;

    private String content;

    private List<Integer> imgurlList;

    private NewType translateToNewType(String typeStr) {
        switch (typeStr) {
            case "国内":
                return NewType.DOMESTIC;
            case "国际":
                return NewType.INTERNATIONAL;
            case "娱乐":
                return NewType.ENTERTAINMENT;
            case "军事":
                return NewType.MILITARY;
            case "体育":
                return NewType.SPORTS;
            case "科技":
                return NewType.TECHNOLOGY;
            default:
                throw new IllegalArgumentException("Unknown type: " + typeStr);
        }
    }

    public New toPO() {
        New newPO = new New();
        newPO.setId(this.id);
        newPO.setType(translateToNewType(this.type));
        newPO.setLikeCount(this.likeCount);
        newPO.setCreateTime(this.createTime);
        newPO.setAuthorId(this.authorId);
        newPO.setTitle(this.title);
        newPO.setContent(this.content);
        newPO.setImgurlList(this.imgurlList);
        return newPO;
    }
}
