//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace MATTANAAPI.Models
{
    using System;
    
    public partial class get_calendar_by_staff_byday_Result
    {
        public string Id { get; set; }
        public string Code { get; set; }
        public string Store { get; set; }
        public string Phone { get; set; }
        public string AddressDetail { get; set; }
        public Nullable<double> Lat { get; set; }
        public Nullable<double> Lng { get; set; }
        public Nullable<System.TimeSpan> CInTime { get; set; }
        public Nullable<System.TimeSpan> COutTime { get; set; }
        public Nullable<int> Perform { get; set; }
        public Nullable<int> InPlan { get; set; }
        public string StaffCheck { get; set; }
        public string StaffCheckName { get; set; }
    }
}