//package conlon.cloud.canal.config;
//
//import com.alibaba.fastjson.JSON;
//import conlon.cloud.canal.entity.UserInfoEntity;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import top.javatool.canal.client.annotation.CanalTable;
//import top.javatool.canal.client.handler.EntryHandler;
//
//@CanalTable(value = "bs_user_info")
//@Component
//@Slf4j
//public class UserHandler implements EntryHandler<UserInfoEntity> {
//
//   /**
//   *  新增操作
//   * @param user
//    */
//   @Override
//    public void insert(UserInfoEntity user) {
//	   //你的逻辑
//        log.info("新增 {}",JSON.toJSONString(user));
//    }
//    /**
//    * 对于更新操作来讲，before 中的属性只包含变更的属性，after 包含所有属性，通过对比可发现那些属性更新了
//   * @param before
//   * @param after
//    */
//    @Override
//    public void update(UserInfoEntity before, UserInfoEntity after) {
//    	//你的逻辑
//        log.info("更新 {} {}",JSON.toJSONString(before),JSON.toJSONString(after));
//    }
//    /**
//    *  删除操作
//    * @param user
//    */
//    @Override
//    public void delete(UserInfoEntity user) {
//       //你的逻辑
//        log.info("删除 {}", JSON.toJSONString(user));
//   }
//}