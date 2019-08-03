package life.majiang.community.controller.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页包装类
 */
@Data
public class PaginationDTO {
    private Integer totalPage;
    private List<QuestionDTO> questions;
    //首页按钮
    private boolean showFirstPage;
    //向前按钮
    private boolean showPrevious;
    //最后页面
    private boolean showEndPage;
    //向后按钮
    private boolean showNext;
    //当前页
    private Integer page;
    //页码数组
    private List<Integer> pages = new ArrayList<Integer>();

    public void setPagination(Integer totalCount, Integer page, Integer size) {
        this.page=page;
        this.totalPage=totalCount;
        if(totalPage>0&&page>totalPage){
            page=totalPage;
        }
        System.out.println("totalPage:"+totalPage);
        pages.add(page);
        for(int i=1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-i);//每次往前加，index：0
            }

            if(page+i<=totalPage){
                pages.add(page+i);
            }
        }
        //是否展示上一页按钮
        if(page==1){
            showPrevious=false;
        }else{
            showPrevious=true;
        }
        //是否展示下一页按钮
        showNext = (page==totalPage?false:true);

       //是否展示首页按钮
        if(pages.contains(1)){
            showFirstPage = false;
        }else{
            showFirstPage = true;
        }
        //是否展示尾页按钮
        if(pages.contains(totalPage)){
            showEndPage = false;
        }else{
            showEndPage = true;
        }
    }

}
