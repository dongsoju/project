package gone;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class HomeVO {
	private int id,location_id,course_id,num,readcnt;
	private String filename, filepath,ptype,type,member_id,name,locname,couname;
	private Date gone_date;
}
