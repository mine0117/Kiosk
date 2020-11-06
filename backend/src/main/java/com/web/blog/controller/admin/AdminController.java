package com.web.blog.controller.admin;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.blog.dao.admin.AdminDao;
import com.web.blog.dao.branch.BranchDao;
import com.web.blog.dao.orderlist.OrderlistDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.branch.Branch;
import com.web.blog.model.orderlist.Orderlist;
import com.web.blog.model.visit.Visit;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.time.LocalDate;
import java.time.LocalDateTime; // Import the LocalDateTime class
import java.time.LocalTime;
import java.util.List;

import javax.transaction.Transactional;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class AdminController {

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private BranchDao branchDao;

    @Autowired
    private OrderlistDao orderlistDao;

    @GetMapping("/admin/getvisitorcount")
    @ApiOperation(value = "오늘 방문자 수")
    public ResponseEntity<Integer> getVisitorCount() throws SQLException, IOException {

        LocalDateTime startDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));
        LocalDateTime endDatetime = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));

        int count = 0;
        try {
            count = adminDao.countByCurrenttimeBetween(startDatetime, endDatetime);
            System.out.println("logger - count: " + count);
            return new ResponseEntity<Integer>(count, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("logger- 방문자 수 에러" + count);
            count = -1;

            return new ResponseEntity<Integer>(count, HttpStatus.NOT_FOUND);
        }

    }

    //TODO: Uid => 이름으로, 방문기록 자세히
    @GetMapping("/admin/getvisitors")
    @ApiOperation(value = "방문기록")
    public ResponseEntity<List<Visit>> getVisitors() throws SQLException, IOException {
        // System.out.println("logger - getVisitors: ");
        List<Visit> list = null;

        try {
            list = adminDao.findAllByOrderByVidDesc();
            return new ResponseEntity<List<Visit>>(list, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<List<Visit>>(list, HttpStatus.NOT_FOUND);
        }

    }


        @PostMapping("/admin/addmenu")
        @ApiOperation(value = "메뉴추가")
        public ResponseEntity<Boolean> addMenu(@RequestBody Branch branch) throws SQLException, IOException {
            // System.out.println("logger - AddMenu: ");
        
            try {
                branchDao.save(branch);    
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
        }
        
        @GetMapping("/admin/menuinfo")
        @ApiOperation(value = "메뉴정보")
        public ResponseEntity<Branch> menuInfo(@RequestParam int menuid) throws SQLException, IOException {
            // System.out.println("logger - : menuInfo: ");
            Branch branch = null;
            try {
                branch = branchDao.findByMenuid(menuid);
                
                return new ResponseEntity<Branch>(branch, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<Branch>(branch, HttpStatus.NOT_FOUND);
            }
        }
        @PutMapping("/admin/updatemenu")
        @ApiOperation(value = "메뉴 수정")
        public ResponseEntity<Boolean> updateMenu(@RequestBody Branch branch) throws SQLException, IOException {
            // System.out.println("logger - : updateMenu: ");
            try {
                branchDao.save(branch);
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
        } 

        @Transactional
        @DeleteMapping("/admin/deletemenu")
        @ApiOperation(value = "메뉴 삭제")
        public ResponseEntity<Boolean> deleteMenu(@RequestParam int menuid) throws SQLException, IOException {
            try {
                branchDao.deleteByMenuid(menuid);
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
        }

        @GetMapping("/admin/getmonthincome")
        @ApiOperation(value = "월별 수익")
        public ResponseEntity<List<?>> getMonthIncome() throws SQLException, IOException {
            // System.out.println("logger - getMonthIncome method: ");
            List<?> op = null;
            try {
                op = orderlistDao.findMonthIncome();                
                return new ResponseEntity<List<?>>(op, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<List<?>>(op, HttpStatus.NOT_FOUND);
            }
        }

        @GetMapping("/admin/getmonthvisitors")
        @ApiOperation(value = "월별 방문자")
        public ResponseEntity<List<?>> getMonthVisitors() throws SQLException, IOException {
            // System.out.println("logger - getMonthVisitors method: ");
            List<?> op = null;
            try {
                op = adminDao.findMonthVisitors();                
                return new ResponseEntity<List<?>>(op, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<List<?>>(op, HttpStatus.NOT_FOUND);
            }
        }
}