package org.example.server.po;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.server.enums.NewType;
import org.example.server.vo.NewVO;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class New {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    //新闻类型
    @Basic
    @Column(name = "type")
    private NewType type;

    //创建时间
    @Basic
    @Column(name = "create_time")
    private Date createTime;

    //作者id
    @Basic
    @Column(name = "author_id")
    private Integer authorId;

    //新闻标题
    @Basic
    @Column(name = "title")
    private String title;

    //新闻内容
    @Basic
    @Column(name = "content")
    private String content;

    //新闻图片列表
    @ElementCollection
    @CollectionTable(name = "img_url_list", joinColumns = @JoinColumn(name = "img_url"))
    @Column(name = "img_url")
    private List<Integer> imgurlList;

    //点赞数
    @Basic
    @Column(name = "like_count")
    private Integer likeCount;

    //收藏数
    @Basic
    @Column(name = "collect_count")
    private Integer collectCount;

    //评论数
    @Basic
    @Column(name = "comment_count")
    private Integer commentCount;

    public NewVO toVO() {
        NewVO newVO = new NewVO();
        newVO.setId(this.id);
        newVO.setType(this.type);
        newVO.setCreateTime(this.createTime);
        newVO.setAuthorId(this.authorId);
        newVO.setTitle(this.title);
        newVO.setContent(this.content);
        newVO.setImgurlList(this.imgurlList);
        return newVO;
    }
}
