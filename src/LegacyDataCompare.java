
public class LegacyDataCompare {

	private static Object[][] data = {
		{1, "inter_freq_load_balance_SUITE", 60, 0, 0},
		{2, "basic_intra_eNB_handover_SUITE", 15, 2, 0},
		{3, "basic_x2_handover_SUITE", 1, 1, 0},
		{4, "x2_handover_error_case_SUITE", 18, 2, 0},
		{5, "packet_forwarding_SUITE", 40, 0, 0},
		{6, "ue_release_SUITE", 10, 0, 0},
		{7, "preemption_SUITE", 54, 0, 0},
		{8, "interfreq_ho_SUITE", 21, 0, 0},
		{9, "rrc_conn_reestablishment_SUITE", 26, 2, 0},
		{10, "multi_target_rrc_conn_reestablishment_SUITE", 74, 6, 28},
		{11, "mp_load_control_SUITE", 4,5,33},
		{12, "basic_s1_handover_SUITE", 74, 4, 0},
		{13, "s1_handover_error_case_SUITE", 22, 0, 0},
		{14, "s1_handover_packet_forwarding_SUITE", 22, 0, 0}
    };
	private static int length = data.length;
	public LegacyDataCompare() {	
	}
	
	public static Integer getTestNumber(String testName){
        
        for (int i = 0; i < length; i++)
        {
           if (data[i][1].equals(testName))
                return (Integer) data[i][0];
        }		
		
		return 0;
	}
	
	public static String compareWithLegacy(String testName, Integer successes, Integer fails, Integer skipped){
		for (int i = 0; i < length; i++)
		{
	       if (data[i][1].equals(testName)){
				if (successes >= (Integer) data[i][2] && fails <= (Integer) data[i][3] && skipped <= (Integer) data[i][4])	return "Yes";
				return "No";
	       }
	 	}		
			
		return "Test Suite not found";
	}
	
//	public getTestNumber

}
