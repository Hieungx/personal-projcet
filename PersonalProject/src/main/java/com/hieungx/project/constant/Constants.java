package com.hieungx.project.constant;

public class Constants {
    public static final String MEDIA_URL = "https://media.cmcglobal.com.vn/";
    public static final String OLD_VALUE = "OLD VALUE";
    public static final String NEW_VALUE = "NEW VALUE";
    public static final String SYSTEM_MAIL = "rts.system@cmcglobal.vn";
    public static final String BASE_URL = "101.99.14.196:8280/#/";
    //	public static final String BASE_URL = "localhost:8080/api";
    public static final String PENDING = "Pending";
    public static final String CONTACTING = "Contacting";
    public static final String INTERVIEW = "Interview";
    public static final String PASSED = "Passed";
    public static final String OFFER = "Offer";
    public static final String ONBOARD = "Onboard";
    public static final String CLOSED = "Closed";

    public static final String SYSTEM = "System";

    public static final String ROLE_HR_MEMBER = "ROLE_HR_MEMBER";
    public static final String REQUEST_CODE = "ORD";
    public static final int FORMAT_NUMBER_OF_REQUEST_CODE = 3;
    public static final int CHARACTER_ZERO = 0;
    public static final String INTERVIEW_STATUS_NEW = "New";
    public static final String DATE_VALID = "Start date or end date valid";
    public static final String JOB_ID_KEY = "CONNECTOR_JOB";
    public static final String YES = "Y";
    public static final String NO = "N";
    public static final String STATUS_JOB_ERROR = "ERROR";

    public static final int ERROR_MESSAGE_SIZE = 2000;
    public static final String STATUS_JOB_COMPLETE = "COMPLETE";

    public static final String TRIGGER_TYPE_CRON = "CRON";
    public static final String TRIGGER_TYPE_MANUAL = "MANUAL";
    public static final String JOB_LISTENER_NAME = "connectorJobListener";
    public static final String FAIL = "FAIL";

    public static final String EXPIRED = "EXPIRED";


    public static class RESPONSE {
        public static final String NOT_FOUND = "Not found!";
        public static final int INTERNAL_SERVER_ERROR = 500;
        public static final String EXITS_CODE = "FAIL";
        public static final String NOT_EXIST = "400";
        public static final String SUCCESS_CODE = "200";
        public static final String SUCCESS_MESSAGE = "Successful";
        public static final String CREATE_SUCCESS = "Create success";
        public static final String UPDATE_SUCCESS = "Update success";

    }

    public static class ExportConstants {
        public static final String NA = "N/A";
        public static final String BLANK = "";
    }

    @Deprecated
    public static class ROLE {
        public static final String ROLE_ADMIN = "RTS-ADMIN";
        public static final String ROLE_TA_LEADER = "RTS-TA-LEAD";
        public static final String ROLE_TA_MANAGER = "RTS-TA-MNG";
        public static final String ROLE_EB = "RTS-EB";
        public static final String ROLE_DEPARTMENT_HEAD = "RTS-DEPARTMENT_HEAD";
    }

    public static class Security {
        public static final String AUTHORIZATION = "Authorization";
        public static final String BEARER = "Bearer ";
    }

    public static class OfferProcessMailRedisType {
        public static final String APPROVE_OFFER_LV_1 = "APPROVE_OFFER_LV_1";
        public static final String APPROVE_OFFER_LV_2 = "APPROVE_OFFER_LV_2";

        public static final String CREATE_OFFER = "CREATE_OFFER";
        public static final String EDIT_OFFER = "EDIT_OFFER";
        public static final String APPROVE_OFFER_LV_2_TO_CANDIDATE = "APPROVE_OFFER_LV_2_TO_CANDIDATE";
        public static final String CANDIDATE_CONFIRM = "CANDIDATE_CONFIRM";
        public static final String REJECT_OFFER_LV_1 = "REJECT_OFFER_LV_1";
        public static final String REJECT_OFFER_LV_2 = "REJECT_OFFER_LV_2";
        public static final String CANDIDATE_REJECT = "CANDIDATE_REJECT";
    }

    public static class KafkaEventType {
        public static final String APPROVE_REQUEST = "APPROVE_REQUEST";

        public static final String CANCEL_REQUEST = "CANCEL_REQUEST";
        public static final String CLOSE_REQUEST = "CLOSE_REQUEST";
        public static final String REOPEN_REQUEST = "REOPEN_REQUEST";
        public static final String EXPIRE_REQUEST = "EXPIRE_REQUEST";
        public static final String APPROVE_EXTEND_REQUEST = "APPROVE_EXTEND_REQUEST";
        public static final String MOVE_SOURCE_TO_APPLICATION = "MOVE_SOURCE_TO_APPLICATION";
        public static final String MOVE_APPLICATION_TO_SOURCE = "MOVE_APPLICATION_TO_SOURCE";
        public static final String MOVE_APPLICATION_TO_QUALIFY = "MOVE_APPLICATION_TO_QUALIFY";
        public static final String REJECT_CANDIDATE = "REJECT_CANDIDATE";
        public static final String MOVE_QUALIFY_TO_INTERVIEW = "MOVE_QUALIFY_TO_INTERVIEW";
        public static final String MOVE_INTERVIEW_TO_OFFER = "MOVE_INTERVIEW_TO_OFFER";
        public static final String QUALIFY_INTERVIEW = "QUALIFY_INTERVIEW";
        public static final String DISQUALIFY_INTERVIEW = "DISQUALIFY_INTERVIEW";
        public static final String CANDIDATE_CONFIRM_OFFER = "CANDIDATE_CONFIRM_OFFER";
        public static final String REPROCESS = "REPROCESS";
        public static final String CONFIRM_ONBOARD = "CONFIRM_ONBOARD";
        public static final String CONFIRM_ONBOARD_EQUAL_TARGET = "CONFIRM_ONBOARD_EQUAL_TARGET";
        public static final String EXTEND_REQUEST = "EXTEND_REQUEST";
        public static final String REJECT_EXTEND = "REJECT_EXTEND";
    }

    public static class OnboardProcessMailRedisType {
        public static final String CREATE_ONBOARD = "CREATE_ONBOARD";
        public static final String UPDATE_ONBOARD = "UPDATE_ONBOARD";
        public static final String CANCEL_ONBOARD = "CANCEL_ONBOARD";
        public static final String CONFIRM_ONBOARD = "CONFIRM_ONBOARD";
    }

    public static class CommonRedisType {
        public static final String UPDATE_TA_LEAD_OR_SUPERVISOR = "UPDATE_TA_LEAD_OR_SUPERVISOR";
    }

    public static class ReportPerformanceRedisType {
        public static final String UPDATE_CONDITION_PERFORMANCE_CONFIG = "UPDATE_CONDITION_PERFORMANCE_CONFIG";
        public static final String CREATE_CONDITION_PERFORMANCE_CONFIG = "CREATE_CONDITION_PERFORMANCE_CONFIG";
    }

    public static class InterviewProcessMailRedisType {
        public static final String CONFIRM_INTERVIEW = "CONFIRM_INTERVIEW";
        public static final String CREATE_INTERVIEW = "CREATE_INTERVIEW";
        public static final String CANCEL_INTERVIEW = "CANCEL_INTERVIEW";
        public static final String EDIT_INTERVIEW = "EDIT_INTERVIEW";
    }

    public static class InterviewRatedStatus {
        public static final String QUALIFY = "QUALIFY";
        public static final String DISQUALIFY = "DISQUALIFY ";
    }

    public static class EntityType {
        public static final String INTERVIEW = "Interview";
        public static final String ONBOARD = "Onboard";
        public static final String OFFER = "Offer";
        public static final String CANDIDATE = "Candidate";
        public static final String GROUP_TA = "GroupTA";
    }

    public static final String TIMEZONE = "Asia/Ho_Chi_Minh";
    public static final String DATE_FORMAT_DDMMYYYY = "dd/MM/yyyy";
    public static final String DATE_FORMAT_DDMMYY_HHMMSS = "dd/MM/yyyy HH:mm:ss";
    public static final String DATE_FORMAT_DDMMYY_HHMM = "dd/MM/yyyy HH:mm";

    public static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static class Status {
        public static final String ACTIVE = "Active";
        public static final String INACTIVE = "Inactive";
        public static final String ALL = "All";

    }

    public static class GroupTARole {
        public static final String ADMIN = "ADMIN";
        public static final String TA_MANAGER = "MANAGER";
        public static final String TA_LEAD = "LEAD";
        public static final String TA_MEMBER = "MEMBER";
        public static final String TA_SUPERVISOR = "SUPERVISOR";
    }

    public static class POARoleKey {
        public static final String ADMIN = "RTS-ADMIN";
        public static final String TA_MANAGER = "RTS-TA-MNG";
        public static final String TA_LEAD = "RTS-TA-LEAD";
        public static final String TA_MEMBER = "RTS-TA";
        public static final String TA_SUPERVISOR = "RTS-SUPERVISOR";
        public static final String HR_MNG = "RTS-HR-MNG";
        public static final String MANAGER = "RTS-MNG";
        public static final String TA_EB = "RTS-EB";
        public static final String CEO = "RTS-CEO";
        public static final String DEPARTMENT_HEAD = "RTS-DEPARTMENT-HEAD";
        public static final String DIVISION_HEAD = "RTS-DIVISION-HEAD";
    }

    public static class POARoleName {
        public static final String ADMIN = "RTS-ADMIN";
        public static final String TA_MANAGER = "RTS-TA-MNG";
        public static final String TA_LEAD = "RTS-TA-LEAD";
        public static final String TA_MEMBER = "RTS-TA";
        public static final String TA_SUPERVISOR = "RTS-SUPERVISOR";
        public static final String HR_MNG = "RTS-HR-MNG";
        public static final String TA_EB = "RTS-EB";
        public static final String CEO = "RTS-CEO";
        //role ver 2
        public static final String DEPARTMENT_HEAD = "RTS-DEPARTMENT-HEAD";
        public static final String DIVISION_HEAD = "RTS-DIVISION-HEAD";
    }

    public static class Action {
        public static final String ASSIGNED = "assigned";
        public static final String UNASSIGNED = "unassigned";
    }

    public static class GroupTAMemberHistory {
        public static final String JOIN = "Join group";
        public static final String LEAVE = "Leave group";
    }

//
//    public static class Step {
//        public static final String ONBOARD = "Onboard";
//        public static final String SOURCE = "Source";
//        public static final String REJECT = "Reject";
//    }

    public static class RequestActionHistory {
        public static final String CREATE = "CREATE";
        public static final String EDIT = "EDIT";
        public static final String APPROVE = "APPROVE";
        public static final String APPROVE_ESTIMATE_DEADLINE = "APPROVE-ESTIMATE-DEADLINE";
        public static final String APPROVE_EXTEND = "APPROVE-EXTEND";
        public static final String ASSIGN = "ASSIGN";
        public static final String REJECT = "REJECT";
        public static final String CANCEL = "CANCEL";
        public static final String CLOSE = "CLOSE";
        public static final String REOPEN = "REOPEN";
        public static final String EXTEND = "EXTEND";
        public static final String ESTIMATE_DEADLINE = "ESTIMATE-DEADLINE";
        public static final String EXPIRE = "EXPIRE";
        public static final String COMMENT = "COMMENT";
    }

    public static class MailConstants {
        // subject
        public static final String SUBJECT_CONFIRM_INTERVIEW = "[RTS - Confirm Interview]";
        public static final String SUBJECT_APPROVE_REQUEST = "[RTS - Approved Request]";
        public static final String SUBJECT_CREATE_REQUEST = "[RTS - Create Request]";

        public static final String SUBJECT_APPROVE_ESTIMATE_DEADLINE_REQUEST = "[RTS - Approved Estimate Deadline Request]";
        public static final String SUBJECT_APPROVE_EXTEND_REQUEST = "[RTS - Approved Extend Request]";
        public static final String SUBJECT_CANCEL_REQUEST = "[RTS - Cancel Request]";

        public static final String SUBJECT_EXPIRE_REQUEST = "[RTS - Expire Request]";
        public static final String SUBJECT_CLOSE_REQUEST = "[RTS - Close Request]";
        public static final String SUBJECT_EXTEND_REQUEST = "[RTS - Extend Request]";

        public static final String SUBJECT_ESTIMATE_DEADLINE_REQUEST = "[RTS - Estimate Deadline Request]";
        public static final String SUBJECT_REOPEN_REQUEST = "[RTS - Reopen Request]";
        public static final String SUBJECT_ASSIGN_REQUEST = "[RTS - Assign Request]";

        public static final String SUBJECT_REJECT_REQUEST = "[RTS - Reject Request]";
        public static final String SUBJECT_REJECT_ESTIMATE_DEADLINE_REQUEST = "[RTS - Reject Estimate Deadline Request]";

        public static final String SUBJECT_REJECT_EXTEND_REQUEST = "[RTS - Reject Extend Request]";

        public static final String SUBJECT_REQUEST_EXPIRED = "[RTS - Request Expired]";
        public static final String SUBJECT_CANDIDATE_INTERVIEW_SCHEDULE = "[RTS - Interview Schedule]";
        public static final String SUBJECT_CANCEL_INTERVIEW = "[RTS - Cancel Interview]";
        public static final String SUBJECT_EDIT_INTERVIEW = "[RTS - Edit Interview]";

        public static final String SUBJECT_APPROVE_OFFER_LEVEL_1 = "[RTS - Approve Offer Level 1]";

        public static final String SUBJECT_CREATE_OFFER = "[RTS - Create Offer]";

        public static final String SUBJECT_EDIT_OFFER = "[RTS - Edit Offer]";

        public static final String SUBJECT_MOVE_QUALIFY_TO_INTERVIEW = "[RTS - Candidates are approved for interview]";

        public static final String SUBJECT_MOVE_APPLICATION_TO_QUALIFY = "[RTS - Candidates are applied]";

        public static final String SUBJECT_REJECT_CANDIDATE = "[RTS - Candidates has been rejected]";

        public static final String SUBJECT_CHANGE_TA_FOLLOW = "[RTS - Change TA Follow of Request]";

        public static final String SUBJECT_ASSIGN_CANDIDATE = "[RTS - Candidates has been assigned]";

        public static final String SUBJECT_APPROVE_OFFER_LEVEL_2 = "[RTS - Approve Offer Level 2]";
        public static final String SUBJECT_CANDIDATE_CONFIRM_OFFER = "[RTS - Candidate Confirm Offer]";
        public static final String SUBJECT_REJECT_OFFER_LV_1 = "[RTS - Reject Offer Level 1]";
        public static final String SUBJECT_REJECT_OFFER_LV_2 = "[RTS - Reject Offer Level 2]";
        public static final String SUBJECT_APPROVE_OFFER_LEVEL_2_TO_CANDIDATE = "[Offer Letter From CMC Global]";

        public static final String SUBJECT_RE_RECRUITMENT_CANDIDATE = "[RTS - Re-Recruitment Candidate]";

        public static final String SUBJECT_CREATE_ONBOARD = "[RTS - Create Onboard]";

        public static final String SUBJECT_UPDATE_ONBOARD = "[RTS - Update Onboard]";

        public static final String SUBJECT_CANCEL_ONBOARD = "[RTS - Cancel Onboard]";

        public static final String SUBJECT_CONFIRM_ONBOARD = "[RTS - Confirm Onboard]";
    }

    public static class DateStringFormat {
        public final static String ddMMyyyy = "dd/MM/yyyy";

        public final static String ddMMyyyyhhmmss = "dd/MM/yyyy HH:mm:ss";

        public final static String hhmmddMMyyyy = "HH:mm dd/MM/yyyy";
    }

    public static class ReportOnboardEventType {
        public static final String CANDIDATE_CONFIRM = "CANDIDATE_CONFIRM";
        public static final String CANCEL_ONBOARD = "CANCEL_ONBOARD";
        public static final String UPDATE_ONBOARD = "UPDATE_ONBOARD";
        public static final String CONFIRM_ONBOARD = "CONFIRM_ONBOARD";
        public static final String REPROCESS = "REPROCESS";
    }

    public static class ConstantKey {
        public static final String DEPARTMENT_NAME = "DEPARTMENT_NAME";
        public static final String DIVISION_NAME = "DIVISION_NAME";
    }

    public static class GetGroupType {
        public static final String DEPARTMENT = "2";
        public static final String DIVISION = "1";
        public static final String ALL = "ALL";
        public static final String FOLLOW_USER = "FOLLOW_USER";
    }

    public static class PerformanceKey {
        public static final String ALLOTMENT = "ALLOTMENT";
        public static final String FINANCIAL_YEAR = "FINANCIAL_YEAR";
        public static final String PERFORMANCE_TYPE = "PERFORMANCE_TYPE";
        public static final String EMPLOYEE_TYPE = "EMPLOYEE_TYPE";
    }

}
