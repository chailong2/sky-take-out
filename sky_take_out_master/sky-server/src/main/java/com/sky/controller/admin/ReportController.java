package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.service.ReportService;
import com.sky.vo.OrderReportVO;
import com.sky.vo.SalesTop10ReportVO;
import com.sky.vo.TurnoverReportVO;
import com.sky.vo.UserReportVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/admin/report")
@Api(tags = "数据统计相关接口")
@Slf4j
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 营业额统计接口
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/turnoverStatistics")
    @ApiOperation("营业额统计")
    public Result<TurnoverReportVO> turnOverStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end
    ){
        log.info("营业额数据统计，{},{}",begin,end);
        TurnoverReportVO turnOverStatistics = reportService.getTurnOverStatistics(begin,end);
        return Result.success(turnOverStatistics);
    }

    /**
     * 用户数据统计接口
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/userStatistics")
    @ApiOperation("用户统计")
    public Result<UserReportVO> userStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end
    ){
        log.info("用户数据统计，{},{}",begin,end);
        UserReportVO userReportVO=reportService.getUserStatistics(begin, end);
        return Result.success(userReportVO);
    }

    /**
     * 用户数据统计接口
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/ordersStatistics")
    @ApiOperation("订单统计")
    public Result<OrderReportVO> orderStatistics(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end
    ){
        log.info("订单数据统计，{},{}",begin,end);
        OrderReportVO orderReportVO=reportService.getOrderStatistics(begin,end);
        return Result.success(orderReportVO);
    }

    /**
     * 销量前十统计接口
     * @param begin
     * @param end
     * @return
     */
    @GetMapping("/top10")
    @ApiOperation("销量前十接口")
    public Result<SalesTop10ReportVO> top10(
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
            @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end
    ){
        log.info("销量排名前十数据统计，{},{}",begin,end);
        SalesTop10ReportVO salesTop10ReportVO=reportService.getSalesTop10(begin,end);
        return Result.success(salesTop10ReportVO);
    }

    /**
     * 导出运营数据报表
     * @param response
     */
    @GetMapping("/export")
    @ApiOperation("导出运营数据报表")
    public void export(HttpServletResponse response){
        reportService.exportBuisinessData(response);
    }
}
