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
    using System.Collections.Generic;
    
    public partial class CalendarWork
    {
        public string Id { get; set; }
        public Nullable<int> CDay { get; set; }
        public Nullable<int> InPlan { get; set; }
        public string TypeId { get; set; }
        public string Notes { get; set; }
        public Nullable<System.TimeSpan> CInTime { get; set; }
        public Nullable<System.TimeSpan> COutTime { get; set; }
        public string DayInWeek { get; set; }
        public Nullable<int> Perform { get; set; }
        public string CDate { get; set; }
        public string AgencyId { get; set; }
    
        public virtual MAgency MAgency { get; set; }
    }
}
