package colon.cloud.search.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * log pilot的实体类
 * </p>
 *
 * @author conlon
 * @since 2020-01-28
 */
@Data
@Accessors(chain = true)
public class LogPilotModel implements Serializable {

    private static final long serialVersionUID = -7457251668169899249L;

    String message;


    String index;


    String docker_container;


    String k8s_container_name;


    String k8s_node_name;
}