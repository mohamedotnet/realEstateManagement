package com.RealEstate.dao;

import com.RealEstate.model.Report;

import java.util.List;

public interface ReportDao {

    void storeReport(Report report);
    Report readReportById(int id);
    void updateReport(Report report);
    void deleteReport(int id);
    List<Report> getReportsList();

}
