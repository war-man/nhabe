//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace MattanaSite.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class MStaff
    {
        public MStaff()
        {
            this.MAgencies = new HashSet<MAgency>();
            this.MOrders = new HashSet<MOrder>();
            this.CalendarInfoes = new HashSet<CalendarInfo>();
        }
    
        public string Id { get; set; }
        public string FullName { get; set; }
        public string Phone { get; set; }
        public string MUser { get; set; }
        public Nullable<int> GroupNumber { get; set; }
        public string IdentityCard { get; set; }
        public Nullable<int> IsLock { get; set; }
    
        public virtual ICollection<MAgency> MAgencies { get; set; }
        public virtual ICollection<MOrder> MOrders { get; set; }
        public virtual ICollection<CalendarInfo> CalendarInfoes { get; set; }
    }
}
