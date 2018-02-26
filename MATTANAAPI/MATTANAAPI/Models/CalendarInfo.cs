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
    
    public partial class CalendarInfo
    {
        public CalendarInfo()
        {
            this.CalendarWithStaffs = new HashSet<CalendarWithStaff>();
            this.CalendarWorks = new HashSet<CalendarWork>();
        }
    
        public string Id { get; set; }
        public Nullable<int> IsLock { get; set; }
        public Nullable<System.DateTime> FDate { get; set; }
        public Nullable<System.DateTime> TDate { get; set; }
        public string Notes { get; set; }
        public Nullable<int> WeekOfYear { get; set; }
    
        public virtual ICollection<CalendarWithStaff> CalendarWithStaffs { get; set; }
        public virtual ICollection<CalendarWork> CalendarWorks { get; set; }
    }
}
