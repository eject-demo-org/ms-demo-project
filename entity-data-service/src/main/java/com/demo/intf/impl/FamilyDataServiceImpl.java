package com.demo.intf.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.stereotype.Service;

import com.demo.file.helper.ExcelFileReader;
import com.demo.intf.IFamilyDataService;
import com.demo.model.FamilyDataModel;
import com.demo.model.FamilyDetailsDataModel;
import com.demo.util.ApplicationConstant;
import com.demo.util.FileResourceUtility;

@Service
public class FamilyDataServiceImpl implements IFamilyDataService {

	@Override
	public FamilyDataModel getFamilyData(String id) {


		StringBuilder dataString = ExcelFileReader.readExcelSingleRow(
				FileResourceUtility.getFileDetails(ApplicationConstant.FAMILY_FILE_TYPE), id);

		if(dataString == null)
			return null;

		return getFamilyDataModelFromDataString(dataString.toString(), id);
	}

	@Override
	public List<FamilyDetailsDataModel> getFamilyDetails(String id) {

		StringBuilder dataString = ExcelFileReader.readExcelMultipleRow(
				FileResourceUtility.getFileDetails(ApplicationConstant.FAMILY_DETAILS_FILE_TYPE), id);
		if(dataString == null)
			return null;

		return getFamilyDetailDataModelFromDataString(dataString.toString(), id);
	}


	private FamilyDataModel getFamilyDataModelFromDataString(String dataString, String id) {		
		FamilyDataModel retObj = new FamilyDataModel();		
		int index = 0;
		System.out.println("DataString:"+dataString);

		for(String data:dataString.split(ApplicationConstant.DATA_DELIMETER)) {
			if(data!=null && !"".equals(data)) {
				switch (index) {
				case 0:
					retObj.setBaseId(data);
					break;
				case 1:
					retObj.setMarriageDate(DateUtil.getJavaDate(Double.parseDouble(data)));
					break;
				case 2:
					retObj.setSpouseName(data);
					break;
				case 3:
					retObj.setSpouseId(data);
					break;
				case 4:
					retObj.setChildrenCount(new BigDecimal(data).intValue());
					break;
				case 5:
					retObj.setFamilyCount(new BigDecimal(data).intValue());
					break;
				case 6:
					retObj.setFamilyHeadId(data);
					break;
				default:
					break;
				}
			}
			index++;
		}		
		return retObj;
	}

	private List<FamilyDetailsDataModel> getFamilyDetailDataModelFromDataString(String dataString, String relationId){

		List<FamilyDetailsDataModel> familyList = new ArrayList<>();

		System.out.println("DataString:"+dataString);

		for(String subString:dataString.split(ApplicationConstant.ROW_DELIMETER)) {
			FamilyDetailsDataModel detail = new FamilyDetailsDataModel(relationId);			
			System.out.println("subString:"+subString);
			int index = 0;
			for(String data:subString.split(ApplicationConstant.DATA_DELIMETER)) {
				System.out.println(index+":data:"+data);
				switch(index) {
				case 1:
					detail.setId(data);
					break;				
				case 2:
					detail.setRelation(new BigDecimal(data).intValue());
					break;
				case 3:				
					detail.setGender(new BigDecimal(data).intValue());
					break;
				case 4:
					detail.setName(data);
					break;
				case 5:
					detail.setDateOfBirth(DateUtil.getJavaDate(Double.parseDouble(data)));							
					break;
				case 6:
					detail.setRelationDesc(data);
					break;				
				default:

					break;
				}
				index++;
			}
			familyList.add(detail);
		}

		return familyList;
	}

}
