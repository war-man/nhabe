﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using MATTANAAPI.Models;
using MATTANAAPI.Util;
using System.Web.Script.Serialization;
using PagedList;


namespace MATTANAAPI.Controllers
{
    public class InfoController : BaseController
    {


        [HttpGet]
        public List<ProductInfoResult> products()
        {

            var arr = db.MProducts.ToList();

            List<ProductInfoResult> results = new List<ProductInfoResult>();

            foreach (var item in arr)
            {
                results.Add(new ProductInfoResult()
                {
                    id = item.Id,
                    code = item.PCode,
                    describes = item.Describes,
                    name = item.PName,
                    price = item.Price
                });
            }


            return results;

        }

        [HttpGet]
        public ResultInfo UpdateAgencyLocation(double lat, double lng, string agencyCode)
        {
                var log = new MongoHistoryAPI()
        {
            APIUrl = "/api/info/updateagencylocation",
            CreateTime = DateTime.Now,
            Sucess = 1
        };

            var result = new ResultInfo()
            {
                id = "1",
                msg = "success"
            };

            try
            {
                var checkAgency = db.MAgencies.Where(p => p.Code == agencyCode).FirstOrDefault();

                if (checkAgency == null)
                    throw new Exception("Sai thông tin");

                checkAgency.Lat = lat;
                checkAgency.Lng = lng;
                db.Entry(checkAgency).State = System.Data.Entity.EntityState.Modified;
                db.SaveChanges();
            }
            catch (Exception e)
            {
                result.id = "0";
                result.msg = e.Message;
                log.Sucess = 0;
            }

            log.ReturnInfo = new JavaScriptSerializer().Serialize(result);

            mongoHelper.createHistoryAPI(log);

            return result;

        }


        [HttpGet]
        public List<NoticeInfo> Notices(string user, int? page)
        {
            List<NoticeInfo> result = new List<NoticeInfo>();

            DateTime current = DateTime.Now;
            DateTime fDate = current.AddMonths(-3);

            int pageSize = 30;
            int pageNumber = (page ?? 1);

            var data = mongoHelper.getNotices(user, fDate, current).OrderByDescending(p => p.Time).ToPagedList(pageNumber, pageSize);

            foreach (var item in data)
            {
                result.Add(new NoticeInfo()
                {
                    id = item.Id.ToString(),
                    message = item.Message,
                    read = item.Read,
                    time = item.Time.Value.ToString("dd/MM/yyyy HH:mm"),
                    title = item.Title
                });
            }

            return result;

        }

        [HttpGet]
        public ResultInfo UpdateNoticeRead(string id)
        {
            mongoHelper.updateNotice(id);

            return new ResultInfo()
            {
                id = "1",
                msg = "success"
            };
        }
    }
}
