package org.example.server.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.server.po.Comment;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class CommentVO {

    private Integer id;

    private Integer authorId;

    private Integer newId;

    private String content;

    private Integer likeCount;

    private Date createTime;

    public Comment toPO() {
        Comment comment = new Comment();
        comment.setId(this.id);
        comment.setAuthorId(this.authorId);
        comment.setNewId(this.newId);
        comment.setContent(this.content);
        comment.setLikeCount(this.likeCount);
        comment.setCreateTime(this.createTime);
        return comment;
    }
}
