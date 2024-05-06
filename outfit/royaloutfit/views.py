import json
from datetime import datetime

from django.contrib import auth
from django.contrib.auth.decorators import login_required
from django.core.files.storage import FileSystemStorage
from django.http import HttpResponse, JsonResponse
from django.shortcuts import render

# Create your views here.
from royaloutfit.models import *


def login1(request):
    return render(request,'Login/loginindex.html')


def logout(request):
    auth.logout(request)
    return render(request,'Login/loginindex.html')


def login_code(request):
    uname=request.POST['textfield']
    pswd=request.POST['textfield2']
    try:
        ob=login_table.objects.get(username=uname,password=pswd)
        if ob.type == "Coordinator":
            auth_obj = auth.authenticate(username='admin', password='admin')
            if auth_obj is not None:
                auth.login(request, auth_obj)
            return HttpResponse('''<script>alert("coordinatorhome");window.location='/Coordinator'</script>''')
        elif ob.type == "manufacturer":
            auth_obj = auth.authenticate(username='admin', password='admin')
            if auth_obj is not None:
                auth.login(request, auth_obj)
            m_obj = manufacturer_table.objects.get(LOGIN_id=ob.id)
            request.session['m_image'] = m_obj.Photo.url
            request.session['lid'] = ob.id
            return HttpResponse('''<script>alert("Manufacturehome");window.location='/manufacture'</script>''')
        elif ob.type == "designers":
            auth_obj = auth.authenticate(username='admin', password='admin')
            if auth_obj is not None:
                auth.login(request, auth_obj)
            request.session['lid']=ob.id
            return HttpResponse('''<script>alert("Designhome");window.location='/designhome'</script>''')
        else:
            return HttpResponse('''<script>alert("Invalid");window.location='/'</script>''')
    except:
        return HttpResponse('''<script>alert("Invalid");window.location='/'</script>''')


@login_required(login_url='/')
def block(request):
    ob=manufacturer_table.objects.all()
    return render(request,'Co-ordinator/block&unblock.html',{'val':ob})


@login_required(login_url='/')
def Coordinator(request):
 return render(request,'Co-ordinator/homeindex.html')


@login_required(login_url='/')
def manufacture(request):
    return render(request,'Manufcture/manufacture_index.html')


@login_required(login_url='/')
def sendreplay(request,id):
    ob=complaint_table.objects.get(id=id)
    request.session['cid']=ob.id
    return render(request,'Co-ordinator/Sendreplay.html')


@login_required(login_url='/')
def add_reply(request):
    a=request.POST['textfield']
    ob=complaint_table.objects.get(id=request.session['cid'])
    ob.reply=a
    ob.Date=datetime.now()
    ob.save()
    return HttpResponse('''<script>alert("Reply Successfully");window.location='/viewcompailnts#about'</script>''')


@login_required(login_url='/')
def verifymanufacture(request):
    ob=manufacturer_table.objects.all()
    return render(request,'Co-ordinator/verifymanufacture.html',{'val':ob})


@login_required(login_url='/')
def searchmanufacture(request):
    a = request.POST['textfield']
    ob = manufacturer_table.objects.filter(Name__istartswith=a)
    if len(ob) == 0:
        return HttpResponse('''<script>alert("Data not found");window.location='/verifymanufacture#about'</script>''')
    else:
        return render(request, 'Co-ordinator/verifymanufacture.html', {'val': ob, 'a': a})


@login_required(login_url='/')
def searchcomplaints(request):
    a = request.POST['textfield']
    ob = complaint_table.objects.filter(USER__Name__istartswith=a)
    if len(ob) == 0:
        return HttpResponse('''<script>alert("Data not found");window.location='/viewcompailnts#about'</script>''')
    else:
        return render(request, 'Co-ordinator/view compailnts.html', {'val': ob, 'a': a})


@login_required(login_url='/')
def searchrating(request):
    a = request.POST['textfield']
    ob = rating_table.objects.filter(Date=a)
    if len(ob) == 0:
        return HttpResponse('''<script>alert("Data not found");window.location='/viewrateing#about'</script>''')
    else:
        return render(request,'Co-ordinator/viewrateing.html',{'val':ob,'dt':a})


@login_required(login_url='/')
def block1(request):
    a = request.POST['textfield']
    ob = manufacturer_table.objects.filter(Name__istartswith=a)
    if len(ob) == 0:
        return HttpResponse('''<script>alert("Data not found");window.location='/block#about'</script>''')
    else:
      return render(request, 'Co-ordinator/block&unblock.html', {'val': ob, 'a': a})


@login_required(login_url='/')
def acceptmanufacture(request,id):
    ob=login_table.objects.get(id=id)
    ob.type='manufacturer'
    ob.save()
    return HttpResponse('''<script>alert("Accepted Successfully");window.location='/verifymanufacture#about'</script>''')



@login_required(login_url='/')
def rejectmanufacture(request,id):
    ob = login_table.objects.get(id=id)
    ob.type = 'Rejected'
    ob.save()
    return HttpResponse('''<script>alert("Rejected Successfully");window.location='/verifymanufacture#about'</script>''')


@login_required(login_url='/')
def blockmanufacture(request,id):
    ob = login_table.objects.get(id=id)
    ob.type = 'Blocked'
    ob.save()
    return HttpResponse('''<script>alert("Blocked Successfully");window.location='/block#about'</script>''')


@login_required(login_url='/')
def unblockmanufacture(request,id):
    ob = login_table.objects.get(id=id)
    ob.type = 'manufacturer'
    ob.save()
    return HttpResponse('''<script>alert("Unblocked Successfully");window.location='/block#about'</script>''')


@login_required(login_url='/')
def downloadmanufacture(request,id):
    ob = login_table.objects.get(id=id)
    ob.type = 'manufacturer'
    ob.save()


@login_required(login_url='/')
def viewcompailnts(request):
    ob = complaint_table.objects.all()
    return render(request,'Co-ordinator/view compailnts.html',{'val':ob})



def Manufacture_signup(request):
    return render(request,'Manufcture/reg_index.html')



def registration(request):
    a=request.POST['textfield']
    b=request.POST['radiobutton']
    c=request.POST['textfield2']
    d=request.POST['textfield3']
    e=request.POST['textfield4']
    f=request.POST['textfield5']
    g=request.FILES['file']
    fn=FileSystemStorage()
    fs=fn.save(g.name,g)
    h=request.POST['textfield7']
    i=request.FILES['file2']
    fs2=fn.save(i.name,i)
    j=request.POST['textfield6']
    k=request.POST['textfield8']
    ob=login_table()
    ob.username=j
    ob.password=k
    ob.type='pending'
    ob.save()
    ob1=manufacturer_table()
    ob1.LOGIN=ob


    ob1.Gender=b
    ob1.Name=a
    ob1.Place=c
    ob1.Post=d
    ob1.Pin=e
    ob1.Phone=f
    ob1.License=fs
    ob1.Email=h
    ob1.Photo=fs2
    ob1.save()

    return HttpResponse('''<script>alert("Registration Successfully");window.location='/'</script>''')



@login_required(login_url='/')
def viewrateing(request):
    ob=rating_table.objects.all()
    return render(request,'Co-ordinator/viewrateing.html',{'val':ob})


@login_required(login_url='/')
def searchviewrating(request):
    a = request.POST['textfield']
    ob = rating_table.objects.filter(Date=a)
    if len(ob) == 0:
        return HttpResponse('''<script>alert("Data not found");window.location='/viewrating#about'</script>''')
    else:
        return render(request,'Manufcture/view rating.html',{'val':ob, 'a': a})


@login_required(login_url='/')
def adddesign(request):
    return render(request,'Designers/adddesign.html')


@login_required(login_url='/')
def editdesign(request, id):
    request.session['did']=id
    ob = designs_tables.objects.get(id=id)
    return render(request,'Designers/editdesign.html', {'val': ob})


@login_required(login_url='/')
def edit_design_post(request):
    try:
        type=request.POST['textfield']
        design = request.FILES['file']
        fs=FileSystemStorage()
        fp=fs.save(design.name,design)
        discription =request.POST['textfield2']
        ob=designs_tables.objects.get(id= request.session['did'])
        ob.type=type
        ob.design=fp
        ob.discription=discription
        print("*******************************", request.session['lid'])
        ob.DESIGNER=designers_table.objects.get(LOGIN__id=request.session['lid'])
        ob.save()
    except:
        type = request.POST['textfield']

        discription = request.POST['textfield2']
        ob=designs_tables.objects.get(id= request.session['did'])
        ob.type = type
        ob.discription = discription
        print("*******************************", request.session['lid'])
        ob.DESIGNER = designers_table.objects.get(LOGIN__id=request.session['lid'])
        ob.save()
    return HttpResponse('''<script>alert("edited Successfully");window.location='/managedesign#about'</script>''')


@login_required(login_url='/')
def deletedesign(request,id):
    ob=designs_tables.objects.get(id=id)
    ob.delete()
    return HttpResponse('''<script>alert("Deleted Successfully");window.location='/managedesign#about'</script>''')


@login_required(login_url='/')
def add_design_post(request):
    type=request.POST['textfield']
    design = request.FILES['file']
    discription = request.POST['textfield2']

    fs=FileSystemStorage()
    fp=fs.save(design.name,design)

    ob=designs_tables()
    ob.type=type
    ob.design=fp
    ob.discription=discription
    ob.DESIGNER=designers_table.objects.get(LOGIN__id=request.session['lid'])
    ob.save()
    return HttpResponse('''<script>alert("Added Successfully");window.location='/managedesign#about'</script>''')


@login_required(login_url='/')
def customisedesign(request):
    ob=custom_desgin_table.objects.all()
    return render(request, 'Designers/customisedesign.html', {'val': ob})


@login_required(login_url='/')
def searchcustomisedesign(request):
    a = request.POST['textfield']
    ob = custom_desgin_table.objects.filter(date=a)
    if len(ob) == 0:
        return HttpResponse('''<script>alert("Data not found");window.location='/customisedesign#about'</script>''')
    else:
        return render(request, 'Designers/customisedesign.html', {'val': ob, 'a': a})


@login_required(login_url='/')
def acceptdesign(request,id):
    ob=custom_desgin_table.objects.get(id=id)
    ob.status='Accepted'
    ob.save()
    return HttpResponse('''<script>alert("Accepted Successfully");window.location='/customisedesign#about'</script>''')


@login_required(login_url='/')
def rejectdesign(request,id):
    ob=custom_desgin_table.objects.get(id=id)
    ob.status='rejected'
    ob.save()
    return HttpResponse('''<script>alert("Rejected Successfully");window.location='/customisedesign#about'</script>''')


@login_required(login_url='/')
def designhome(request):
    return render(request,'Designers/designer_index.html')


@login_required(login_url='/')
def managedesign(request):
    ob=designs_tables.objects.filter(DESIGNER__LOGIN=request.session['lid'])
    return render(request,'Designers/mngs dsgn.html',{'val':ob})


@login_required(login_url='/')
def more_designs(request, id):
    request.session['design_id'] = id
    ob = MoreDesignTable.objects.filter(DESIGN_id=id)
    return render(request, 'Designers/more_designs.html', {'val': ob})


@login_required(login_url='/')
def add_more_designs(request):
    return render(request, "Designers/add_more_design.html")

@login_required(login_url='/')
def add_more_design_post(request):
    design = request.FILES['file']
    fss = FileSystemStorage()
    design_file = fss.save(design.name, design)
    ob = MoreDesignTable()
    ob.DESIGN = designers_table.objects.get(id=request.session['design_id'])
    ob.design_more = design_file
    ob.save()
    return HttpResponse('''<script>alert("Successfully added");window.location='/managedesign#about'</script>''')


@login_required(login_url='/')
def adddesigners(request):
    return render(request,'Manufcture/add designers.html')


@login_required(login_url='/')
def addmaterial(request):
    return render(request,'Manufcture/add material.html')


@login_required(login_url='/')
def add_material_post(request):
    material=request.POST['textfield']
    photo = request.FILES['file']
    stock = request.POST['textfield3']
    details = request.POST['textfield4']
    price = request.POST['textfield2']
    fs=FileSystemStorage()
    fp=fs.save(photo.name,photo)

    ob=materials_table()
    ob.manufacture=manufacturer_table.objects.get(LOGIN__id=request.session['lid'])
    ob.material=material
    ob.photo=fp
    ob.stock=stock
    ob.details=details
    ob.price=price
    ob.save()
    return HttpResponse('''<script>alert("Added Successfully");window.location='/managematerials#about'</script>''')


@login_required(login_url='/')
def edit_materials(request,id):
    request.session['oo']=id
    ob = materials_table.objects.get(id=id)
    return render(request,'Manufcture/edit_material.html', {'val': ob})


@login_required(login_url='/')
def edit_material_post(request):
   try:
       material = request.POST['textfield']
       price = request.POST['textfield2']
       stock = request.POST['textfield3']
       details = request.POST['textfield4']
       photo = request.FILES['file']
       fs = FileSystemStorage()
       fp = fs.save(photo.name, photo)

       ob = materials_table.objects.get(id=request.session['oo'])
       ob.material = material
       ob.price = price
       ob.stock = stock
       ob.details = details
       ob.photo = fp
       ob.save()
       return HttpResponse('''<script>alert("Edited Successfully");window.location='/managematerials#about'</script>''')
   except:
       material = request.POST['textfield']
       price = request.POST['textfield2']
       stock = request.POST['textfield3']
       details = request.POST['textfield4']
       photo = request.FILES['file']
       fs = FileSystemStorage()
       fp = fs.save(photo.name, photo)


       ob = materials_table.objects.get(id=request.session['oo'])
       ob.material = material
       ob.price = price
       ob.stock = stock
       ob.details = details
       ob.photo = photo
       ob.save()
       return HttpResponse('''<script>alert("Edited Successfully");window.location='/managematerials#about'</script>''')


@login_required(login_url='/')
def delete_materials(request, id):
       ob = materials_table.objects.get(id=id)
       ob.delete()
       return HttpResponse('''<script>alert("Deleted Successfully");window.location='/managematerials#about'</script>''')


@login_required(login_url='/')
def searchmaterials(request):
    a = request.POST['textfield']
    ob = materials_table.objects.filter(material__istartswith=a)
    return render(request, 'Manufcture/manage materials.html', {'val': ob})


@login_required(login_url='/')
def addts(request):
    return render(request,'Manufcture/add ts.html')


@login_required(login_url='/')
def assigntotrailorshop(request,id):
    request.session['odid']=id
    ob=tailorshop_table.objects.all()
    return render(request,'Manufcture/assign to trailorshop.html',{'val':ob})


@login_required(login_url='/')
def assigntlr(request):
    tid=request.POST['select']
    ob=assign_table()
    ob.orderinfo=orderinfo_table.objects.get(id=request.session['odid'])
    ob.tailorshop=tailorshop_table.objects.get(id=tid)
    ob.date=datetime.today()
    ob.status="pending"
    ob.save()
    return HttpResponse('''<script>alert("Assigned");window.location='/vieworder#about'</script>''')


@login_required(login_url='/')
def Block_unblockdesigners(request):
    ob=designers_table.objects.all()
    return render(request,'Manufcture/Block&unblockdesigners.html',{'val':ob})


@login_required(login_url='/')
def block_unblocktrailorshop(request):
    ob=tailorshop_table.objects.all()
    return render(request,'Manufcture/block&unblocktrailorshop.html',{'val':ob})


@login_required(login_url='/')
def srch_block_unblocktrailorshop(request):
    name=request.POST['textfield']
    ob=tailorshop_table.objects.filter(Name__istartswith=name)
    if len(ob) == 0:
        return HttpResponse('''<script>alert("Data not found");window.location='/block_unblocktrailorshop#about'</script>''')
    else:
        return render(request,'Manufcture/block&unblocktrailorshop.html',{'val':ob, 'a': name})


@login_required(login_url='/')
def blocktailorshop(request,id):
    ob=login_table.objects.get(id=id)
    ob.type="blocked"
    ob.save()
    return HttpResponse('''<script>alert("blocked Successfully");window.location='/block_unblocktrailorshop#about'</script>''')


@login_required(login_url='/')
def unblocktailorshop(request,id):
    ob=login_table.objects.get(id=id)
    ob.type="tailershop"
    ob.save()
    return HttpResponse('''<script>alert("unblocked Successfully");window.location='/Block_unblockdesigners#about'</script>''')


@login_required(login_url='/')
def blockdesigners(request,id):
    ob=login_table.objects.get(id=id)
    ob.type="blocked"
    ob.save()
    return HttpResponse('''<script>alert("blocked Successfully");window.location='/Block_unblockdesigners#about'</script>''')


@login_required(login_url='/')
def unblockdesigners(request,id):
    ob=login_table.objects.get(id=id)
    ob.type="designers"
    ob.save()
    return HttpResponse('''<script>alert("unblocked Successfully");window.location='/Block_unblockdesigners#about'</script>''')


@login_required(login_url='/')
def searchtailorshop(request):
    a = request.POST['textfield']
    ob = designers_table.objects.filter(Name__istartswith=a)
    return render(request,'Manufcture/block&unblocktrailorshop.html',{'val': ob})


@login_required(login_url='/')
def searchdesigners(request):
    a = request.POST['textfield']
    ob = designers_table.objects.filter(Name__istartswith=a)
    if len(ob) == 0:
        return HttpResponse('''<script>alert("Data not found");window.location='/managedesign#about'</script>''')
    else:
        return render(request,'Manufcture/Block&unblockdesigners.html',{'val': ob, 'a': a})


@login_required(login_url='/')
def searchmanagematerials(request):
    a = request.POST['textfield']
    ob = materials_table.objects.filter(material__istartswith=a)
    if len(ob) == 0:
        return HttpResponse('''<script>alert("Data not found");window.location='/managematerials#about'</script>''')
    else:
        return render(request,'Manufcture/manage materials.html',{'val': ob, 'a': a})


@login_required(login_url='/')
def managematerials(request):
    ob=materials_table.objects.all()
    return render(request,'Manufcture/manage materials.html',{'val':ob})


@login_required(login_url='/')
def managetailorshop(request):
    ob=tailorshop_table.objects.all()
    return render(request,'Manufcture/managetaliorshop.html',{'val':ob})


def searchtailorshop1(request):
    a = request.POST['textfield']
    ob = tailorshop_table.objects.filter(Name__istartswith=a)
    if len(ob) == 0:
        return HttpResponse('''<script>alert("Data not found");window.location='/managetailorshop#about'</script>''')
    else:
        return render(request,'Manufcture/managetaliorshop.html',{'val':ob, 'a': a})


@login_required(login_url='/')
def add_taliorshop_post(request):
    Name=request.POST['textfield']
    Place = request.POST['textfield2']
    Post = request.POST['textfield4']
    Pin = request.POST['textfield5']
    Phone = request.POST['textfield3']
    Email = request.POST['textfield6']
    License = request.FILES['file']
    Photo = request.FILES['file2']
    username = request.POST['textfield7']
    password = request.POST['textfield8']
    fs=FileSystemStorage()
    fp=fs.save(License.name,License)

    obj=login_table()
    obj.username=username
    obj.password=password
    obj.type='taliorshop'
    obj.save()

    ob=tailorshop_table()
    ob.LOGIN=obj
    ob.Name=Name
    ob.Photo=Photo
    ob.Place=Place
    ob.Post=Post
    ob.Pin=Pin
    ob.Phone=Phone
    ob.Email=Email
    ob.License=fp
    ob.save()
    return HttpResponse('''<script>alert("Added Successfully");window.location='/managetailorshop#about'</script>''')


@login_required(login_url='/')
def edit_tailorshop(request,id):
    request.session['oo']=id
    ob = tailorshop_table.objects.get(id=id)
    return render(request,'Manufcture/edit_taliorshop.html', {'val': ob})


@login_required(login_url='/')
def delete_taliorshop(request,id):
    ob=tailorshop_table.objects.get(id=id)
    ob.delete()
    return HttpResponse('''<script>alert("Deleted Successfully");window.location='/managetailorshop#about'</script>''')


@login_required(login_url='/')
def edit_taliorshop_post(request):
   try:
       Name = request.POST['textfield']
       Place = request.POST['textfield2']
       Post = request.POST['textfield4']
       Pin = request.POST['textfield5']
       Phone = request.POST['textfield3']
       Email = request.POST['textfield6']
       License = request.FILES['file']
       Photo = request.FILES['file2']
       fs = FileSystemStorage()
       fp = fs.save(License.name, License)

       ob = tailorshop_table.objects.get(id=request.session['oo'])
       ob.Name = Name
       ob.Photo = Photo
       ob.Place = Place
       ob.Post = Post
       ob.Pin = Pin
       ob.Phone = Phone
       ob.Email = Email
       ob.License = fp
       ob.save()
       return HttpResponse('''<script>alert("Edited Successfully");window.location='/managetailorshop#about'</script>''')
   except:
       Name = request.POST['textfield']
       Place = request.POST['textfield2']
       Post = request.POST['textfield4']
       Pin = request.POST['textfield5']
       Phone = request.POST['textfield3']
       Email = request.POST['textfield6']


       ob = tailorshop_table.objects.get(id=request.session['oo'])
       ob.Name = Name
       ob.Place = Place
       ob.Post = Post
       ob.Pin = Pin
       ob.Phone = Phone
       ob.Email = Email
       ob.save()
       return HttpResponse('''<script>alert("Edited Successfully");window.location='/managetailorshop#about'</script>''')


@login_required(login_url='/')
def manufacturehome(request):
    return render(request,'Manufcture/manufacturehome.html')


@login_required(login_url='/')
def manage_designers(request):
    ob=designers_table.objects.all()
    return render(request,'Manufcture/mnge desgnrs.html',{'val':ob})


@login_required(login_url='/')
def editdesigners(request, id):
    request.session['did']=id
    ob = designers_table.objects.get(id=id)
    return render(request,'Manufcture/editdesigners.html', {'val': ob})


@login_required(login_url='/')
def edit_designers_post(request):
   try:
       Name = request.POST['textfield']
       Gender = request.POST['radiobutton']
       Place = request.POST['textfield5']
       Post = request.POST['textfield3']
       Pin = request.POST['textfield4']
       Phone = request.POST['textfield2']
       Email = request.POST['textfield6']
       Experience = request.POST['textfield7']
       Certificate = request.FILES['file']
       fs = FileSystemStorage()
       fp = fs.save(Certificate.name, Certificate)

       ob = designers_table.objects.get(id=request.session['did'])
       ob.Name = Name
       ob.Gender = Gender
       ob.Place = Place
       ob.Post = Post
       ob.Pin = Pin
       ob.Phone = Phone
       ob.Email = Email
       ob.Experience = Experience
       ob.Certificate = fp
       ob.save()
       return HttpResponse('''<script>alert("Edit Successfully");window.location='/manage_designers#about'</script>''')
   except:
       Name = request.POST['textfield']
       Gender = request.POST['radiobutton']
       Place = request.POST['textfield5']
       Post = request.POST['textfield3']
       Pin = request.POST['textfield4']
       Phone = request.POST['textfield2']
       Email = request.POST['textfield6']
       Experience = request.POST['textfield7']


       ob = designers_table.objects.get(id=request.session['did'])
       ob.Name = Name
       ob.Gender = Gender
       ob.Place = Place
       ob.Post = Post
       ob.Pin = Pin
       ob.Phone = Phone
       ob.Email = Email
       ob.Experience = Experience
       ob.save()
       return HttpResponse('''<script>alert("Edit Successfully");window.location='/manage_designers#about'</script>''')


@login_required(login_url='/')
def add_designers_post(request):
    Name=request.POST['textfield']
    Gender = request.POST['radiobutton']
    Place = request.POST['textfield5']
    Post = request.POST['textfield3']
    Pin = request.POST['textfield4']
    Phone = request.POST['textfield2']
    Email = request.POST['textfield6']
    Experience = request.POST['textfield7']
    Certificate = request.FILES['file']
    username = request.POST['textfield9']
    password = request.POST['textfield10']
    fs=FileSystemStorage()
    fp=fs.save(Certificate.name,Certificate)

    obj=login_table()
    obj.username=username
    obj.password=password
    obj.type='designers'
    obj.save()

    ob=designers_table()
    ob.LOGIN=obj
    ob.Name=Name
    ob.Gender=Gender
    ob.Place=Place
    ob.Post=Post
    ob.Pin=Pin
    ob.Phone=Phone
    ob.Email=Email
    ob.Experience=Experience
    ob.Certificate=fp
    ob.save()
    return HttpResponse('''<script>alert("Added Successfully");window.location='/manage_designers#about'</script>''')


@login_required(login_url='/')
def deletedesigners(request,id):
    ob=designers_table.objects.get(id=id)
    ob.delete()
    return HttpResponse('''<script>alert("Deleted Successfully");window.location='/manage_designers#about'</script>''')


@login_required(login_url='/')
def searchdesigners(request):
    a = request.POST['textfield']
    ob = designers_table.objects.filter(Name__istartswith=a)
    if len(ob) == 0:
        return HttpResponse(
            '''<script>alert("Data not found");window.location='/manage_designers#about'</script>''')
    else:
        return render(request, 'Manufcture/mnge desgnrs.html', {'val': ob, 'a': a})


@login_required(login_url='/')
def searchdesign(request):
    a = request.POST['textfield']
    ob = designs_tables.objects.filter(type__istartswith=a)
    if len(ob) == 0:
        return HttpResponse('''<script>alert("Data not found");window.location='/managedesign#about'</script>''')
    else:
        return render(request, 'Designers/mngs dsgn.html', {'val': ob, 'a': a})


@login_required(login_url='/')
def vieworderstatus(request):
    ob = assign_table.objects.all()
    return render(request,'Manufcture/veiw order status.html',{'val':ob})


@login_required(login_url='/')
def searchvieworderstatus(request):
    a = request.POST['textfield']
    ob = assign_table.objects.filter(date=a)
    if len(ob) == 0:
        return HttpResponse('''<script>alert("Data not found");window.location='/vieworderstatus#about'</script>''')
    else:
        return render(request,'Manufcture/veiw order status.html',{'val':ob, 'a': a})


@login_required(login_url='/')
def viewrating(request):
    ob=rating_table.objects.all()
    return render(request,'Manufcture/view rating.html',{'val':ob})


@login_required(login_url='/')
def vieworder(request):
    ob=orderinfo_table.objects.all()
    return render(request,'Manufcture/vieworders.html',{'val':ob})


@login_required(login_url='/')
def searchvieworder(request):
    a = request.POST['textfield']
    ob = orderinfo_table.objects.filter(date=a)
    if len(ob) == 0:
        return HttpResponse('''<script>alert("Data not found");window.location='/vieworder#about'</script>''')

    else:
        return render(request,'Manufcture/vieworders.html',{'val':ob, 'a': a})



@login_required(login_url='/')
def chatwithuser(request):
    ob = user_table.objects.all()
    return render(request,"Designers/fur_chat.html",{'val':ob})


def delete_design(request, id):
    ob = MoreDesignTable.objects.get(id=id)
    ob.delete()
    return HttpResponse('''<script>alert("Deleted");window.location='/managedesign#about'</script>''')


# /////////////////////////////////////////////// webservice /////////////////////////////////////////////////
# ////////////////////////////////////////////////// user //////////////////////////////////


def login_code_and(request):
    username = request.POST['username']
    password = request.POST['password']
    try:
        users = login_table.objects.get(username=username, password=password)
        if users is None:
            data = {"task": "invalid"}

        elif users.type == "user":
            data = {"task": "user", "id": users.id, "type": users.type}
            r = json.dumps(data)
            return HttpResponse(r)
        elif users.type == "tailershop":
            data = {"task": "tailor", "id": users.id, "type": users.type}
            r = json.dumps(data)
            return HttpResponse(r)
        else:
            data = {"task": "valid", "id": users.id, "type": users.type}
            r = json.dumps(data)
            return HttpResponse(r)
    except:
        data = {"task": "invalid"}
        r = json.dumps(data)
        print(r)
        return HttpResponse(r)



def user_register(request):
    name = request.POST['Name']
    place = request.POST['Place']
    post_office = request.POST['Post']
    pin_code = request.POST['Pin']
    gender = request.POST['gender']
    phone = request.POST['Phone']
    email = request.POST['email']
    photo = request.FILES['photo']
    fss = FileSystemStorage()
    photo_file = fss.save(photo.name, photo)
    username = request.POST['Username']
    password = request.POST['Password']

    lob = login_table()
    lob.username = username
    lob.password = password
    lob.type = 'user'
    lob.save()

    user_obj = user_table()
    user_obj.Name = name
    user_obj.Gender = gender
    user_obj.Place = place
    user_obj.Post = post_office
    user_obj.Pin = pin_code
    user_obj.Phone = phone
    user_obj.Email = email
    user_obj.Photo = photo_file
    user_obj.LOGIN = lob
    user_obj.save()
    data = {"task": "success"}
    r = json.dumps(data)
    return HttpResponse(r)


def send_complaint(request):
    complaints = request.POST["complaint"]
    u_id = request.POST["lid"]
    date = datetime.now()
    reply = "waiting"
    complaint_obj = complaint_table()
    complaint_obj.complaint = complaints
    complaint_obj.Date = date
    complaint_obj.reply = reply
    complaint_obj.USER = user_table.objects.get(LOGIN_id=u_id)
    complaint_obj.save()
    data = {'task': 'success'}
    r = json.dumps(data)
    return HttpResponse(r)


def complaints_reply(request):
    user_id = request.POST['lid']
    print("****************", user_id)
    complaint_obj = complaint_table.objects.filter(USER__LOGIN_id=user_id)
    data = []
    for i in complaint_obj:
        row = {'complaint': i.complaint, 'reply': i.reply, 'date': str(i.Date)}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)


def send_rating(request):
    rating = request.POST["rating"]
    review = request.POST["review"]
    u_id = request.POST["lid"]
    date = datetime.now()
    rating_obj = rating_table()
    rating_obj.rating = rating
    rating_obj.Date = date
    rating_obj.Reviews = review
    rating_obj.USER = user_table.objects.get(LOGIN_id=u_id)
    rating_obj.save()
    data = {'task': 'success'}
    r = json.dumps(data)
    return HttpResponse(r)


def view_design(request):
    user_id = request.POST['lid']
    print("****************", user_id)
    design_obj = designs_tables.objects.all()
    data = []
    for i in design_obj:
        row = {'designer_name': i.DESIGNER.Name, 'design': str(i.design.url), 'type': i.type, 'description': i.discription,
               'design_id': i.id}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)


def view_materials(request):
    user_id = request.POST['lid']
    print("****************", user_id)
    material_obj = materials_table.objects.all()
    data = []
    for i in material_obj:
        row = {'material': i.material, 'photo': str(i.photo.url), 'price': i.price, 'stock': i.stock,
               'details': i.details, 'material_id': i.id}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)


def view_tailors(request):
    # user_id = request.POST['lid']
    # print("****************", user_id)
    tailor_obj = tailorshop_table.objects.all()
    data = []
    for i in tailor_obj:
        row = {'Name': i.Name, 'tailor_id': i.LOGIN.id}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)


def view_designers(request):
    user_id = request.POST['lid']
    print("****************", user_id)
    tailor_obj = designers_table.objects.all()
    data = []
    for i in tailor_obj:
        row = {'Name': i.Name, 'designer_id': i.LOGIN_id}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)


def view_designers_drop(request):
    user_id = request.POST['lid']
    print("****************", user_id)
    tailor_obj = designers_table.objects.all()
    data = []
    for i in tailor_obj:
        row = {'Name': i.Name, 'designer_id': i.id}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)


def view_message2(request):
    print("&&&&&&&&&&&&&&&", request.POST)
    fromid = request.POST['fid']
    toid = request.POST['toid']
    mid = request.POST['lastmsgid']

    ob1 = chat.objects.filter(fromid__id=fromid, toid__id=toid, id__gt=mid)
    ob2 = chat.objects.filter(fromid__id=toid, toid__id=fromid, id__gt=mid)
    ob = ob1.union(ob2)
    ob = ob.order_by("id")
    data = []
    for i in ob:
        r = {"msgid": i.id, "date": str(i.date), "message": i.msg, "fromid": i.fromid.id}
        data.append(r)
    print("############", data)
    if len(data) > 0:
        return JsonResponse({"status": "ok", "res1": data})
    else:
        return JsonResponse({"status": "na"})


def in_message2(request):
    print("&&&&&&&&&innnnnnnnnnnnnn&&&&&&", request.POST)

    fromid = request.POST['fid']
    toid = request.POST['toid']
    message = request.POST['msg']
    chat_obj = chat()
    chat_obj.fromid = login_table.objects.get(id=fromid)
    chat_obj.toid = login_table.objects.get(id=toid)
    chat_obj.msg = message
    chat_obj.date = datetime.now().today()
    chat_obj.save()
    data = {"status": "send"}
    r = json.dumps(data)
    return HttpResponse(r)


def order(request):
    design_id = request.POST['design_id']
    material_id = request.POST['material_id']
    u_id = request.POST['lid']
    details = request.POST['details']
    order_obj = orderinfo_table()
    order_obj.USER = user_table.objects.get(LOGIN_id=u_id)
    order_obj.DESIGNS = designs_tables.objects.get(id=design_id)
    order_obj.materials = materials_table.objects.get(id=material_id)
    order_obj.date = datetime.now().today()
    order_obj.amount = 0.0
    order_obj.details = details
    order_obj.status = "pending"
    order_obj.save()
    material_obj = materials_table.objects.get(id=material_id)
    material_obj.stock = material_obj.stock-1
    material_obj.save()
    data = {"task": "success"}
    r = json.dumps(data)
    return HttpResponse(r)


def view_more_design(request):
    design_id = request.POST['design_id']
    print("****************", design_id)
    tailor_obj = MoreDesignTable.objects.filter(DESIGN_id=design_id)
    data = []
    for i in tailor_obj:
        row = {'design': str(i.design_more.url), 'design_id': i.id}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)



# ///////////////////////////////////////////// tailor ////////////////////////////////////


def view_assigned_work(request):
    user_id = request.POST['lid']
    print("****************", user_id)
    assign_obj = assign_table.objects.filter(tailorshop__LOGIN_id=user_id)
    data = []
    for i in assign_obj:
        row = {'username': i.orderinfo.USER.Name, 'Gender': i.orderinfo.USER.Gender,
               'address': i.orderinfo.USER.Place + ", " + i.orderinfo.USER.Post + ", " + str(i.orderinfo.USER.Pin),
               'Phone': i.orderinfo.USER.Phone, 'design': str(i.orderinfo.DESIGNS.design.url),
               'type': i.orderinfo.DESIGNS.type, 'photo': str(i.orderinfo.materials.photo.url),
               'material': i.orderinfo.materials.material, 'date': str(i.date), 'status': i.status,
               'details': i.orderinfo.details, 'user_photo': str(i.orderinfo.USER.Photo.url), 'assign_id': i.id}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)


def view_custom_design(request):
    tailor_id = request.POST['lid']
    print("****************", tailor_id)
    assign_obj = assign_table.objects.filter(tailorshop__LOGIN_id=tailor_id)
    data = []
    data1 = []
    for i in assign_obj:
        data.append(i.orderinfo.USER.id)

    design_obj = custom_desgin_table.objects.filter(USER_id__in=data)

    for i in design_obj:
        row = {'username': i.USER.Name, 'Phone': i.USER.Phone, 'user_image': str(i.USER.Photo.url),
               'designer_name': i.DESIGNER.Name, 'date': str(i.date), 'status': i.status,
               'design_details': i.design_details}
        data1.append(row)
    r = json.dumps(data1)
    print("******************", data1)
    return HttpResponse(r)


def view_user(request):
    user_obj = user_table.objects.all()
    data = []
    for i in user_obj:
        row = {'Name': i.Name, 'user_id': i.LOGIN.id}
        data.append(row)
    r = json.dumps(data)
    return HttpResponse(r)


def update_status(request):
    assign_id = request.POST['assign_id']
    status = request.POST['status']
    ob = assign_table.objects.get(id=assign_id)
    ob.status = status
    ob.save()
    data = {"task": "success"}
    r = json.dumps(data)
    return HttpResponse(r)


def view_user_custom_design(request):
    user_id = request.POST['lid']
    print("****************", user_id)
    data = []
    design_obj = custom_desgin_table.objects.filter(USER__LOGIN_id=user_id)

    for i in design_obj:
        row = {'designer_name': i.DESIGNER.Name, 'date': str(i.date), 'status': i.status,
               'design_details': i.design_details}
        data.append(row)
    r = json.dumps(data)
    print("******************", data)
    return HttpResponse(r)


def send_custom_design(request):
    print("*************", request.POST)
    designer_id = request.POST['designer_id']
    user_lid = request.POST['lid']
    details = request.POST['details']
    ob = custom_desgin_table()
    ob.USER = user_table.objects.get(LOGIN_id=user_lid)
    ob.date = datetime.now().today()
    ob.status = "pending"
    ob.DESIGNER = designers_table.objects.get(id=designer_id)
    ob.design_details = details
    ob.save()
    data = {"task": "success"}
    r = json.dumps(data)
    return HttpResponse(r)




def chatview(request):
    ob = user_table.objects.all()
    d=[]
    for i in ob:
        r={"name":i.Name,'photo':str(i.Photo.url),'email':i.Email,'loginid':i.LOGIN.id}
        d.append(r)
    return JsonResponse(d, safe=False)


def coun_insert_chat(request,msg,id):
    print("===",msg,id)
    ob=chat()
    ob.fromid=login_table.objects.get(id=request.session['lid'])
    ob.toid=login_table.objects.get(id=id)
    ob.msg=msg
    ob.date=datetime.now().strftime("%Y-%m-%d")
    ob.save()

    return JsonResponse({"task":"ok"})
    # refresh messages chatlist



def coun_msg(request,id):
    ob1=chat.objects.filter(fromid__id=id,toid__id=request.session['lid'])
    ob2=chat.objects.filter(fromid__id=request.session['lid'],toid__id=id)
    combined_chat = ob1.union(ob2)
    combined_chat=combined_chat.order_by('id')
    res=[]
    for i in combined_chat:
        res.append({"from_id":i.fromid.id,"msg":i.msg,"date":i.date,"chat_id":i.id})

    obu=user_table.objects.get(LOGIN__id=id)

    return JsonResponse({"data":res,"name":obu.Name,"photo":str(obu.Photo.url),"user_lid":obu.LOGIN.id})








