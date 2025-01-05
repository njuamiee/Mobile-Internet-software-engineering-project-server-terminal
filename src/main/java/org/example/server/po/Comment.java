package org.example.server.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.server.vo.CommentVO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Comment {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    //评论作者id
    @Basic
    @Column(name = "author_id")
    private Integer authorId;

    //评论新闻id
    @Basic
    @Column(name = "news_id")
    private Integer newId;

    //评论内容
    @Basic
    @Column(name = "content")
    private String content;

    //评论时间
    @Basic
    @Column(name = "create_time")
    private Date createTime;

    //评论点赞数
    @Basic
    @Column(name = "like_count")
    private Integer likeCount;

    public CommentVO toVO() {
        CommentVO commentVO = new CommentVO();
        commentVO.setId(this.id);
        commentVO.setAuthorId(this.authorId);
        commentVO.setNewId(this.newId);
        commentVO.setContent(this.content);
        commentVO.setCreateTime(this.createTime);
        commentVO.setLikeCount(this.likeCount);
        return commentVO;
    }
}
