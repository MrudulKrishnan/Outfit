from django.db import models

# Create your models here.


class login_table(models.Model):
    username=models.CharField(max_length=100)
    password=models.CharField(max_length=90)
    type=models.CharField(max_length=90)


class manufacturer_table(models.Model):
    LOGIN=models.ForeignKey(login_table,on_delete=models.CASCADE)
    Name=models.CharField(max_length=100)
    Gender=models.CharField(max_length=90)
    Place=models.CharField(max_length=100)
    Post=models.CharField(max_length=100)
    Pin=models.IntegerField()
    Phone=models.BigIntegerField()
    License=models.FileField()
    Email=models.CharField(max_length=100)
    Photo=models.FileField()


class user_table(models.Model):
    LOGIN=models.ForeignKey(login_table,on_delete=models.CASCADE)
    Name=models.CharField(max_length=100)
    Gender=models.CharField(max_length=90)
    Place=models.CharField(max_length=100)
    Post=models.CharField(max_length=100)
    Pin=models.IntegerField()
    Phone=models.BigIntegerField()
    Email=models.CharField(max_length=100)
    Photo=models.FileField()


class complaint_table(models.Model):
    USER=models.ForeignKey(user_table,on_delete=models.CASCADE)
    complaint=models.TextField()
    Date=models.DateField()
    reply=models.CharField(max_length=100)


class designers_table(models.Model):
    LOGIN=models.ForeignKey(login_table, on_delete=models.CASCADE)
    Name=models.CharField(max_length=100)
    Gender=models.CharField(max_length=90)
    Place=models.CharField(max_length=100)
    Post=models.CharField(max_length=100)
    Pin=models.IntegerField()
    Phone=models.BigIntegerField()
    Email=models.CharField(max_length=100)
    Experience=models.CharField(max_length=100)
    Certificate=models.FileField()

class tailorshop_table(models.Model):
    LOGIN = models.ForeignKey(login_table, on_delete=models.CASCADE)
    Name = models.CharField(max_length=100)
    Place = models.CharField(max_length=100)
    Post = models.CharField(max_length=100)
    Pin = models.IntegerField()
    Phone = models.BigIntegerField()
    License = models.FileField()
    Email = models.CharField(max_length=100)
    Photo = models.FileField()


class rating_table(models.Model):
    USER = models.ForeignKey(user_table, on_delete=models.CASCADE)
    Reviews=models.TextField()
    rating=models.FloatField()
    Date=models.DateField()


class designs_tables(models.Model):
    type=models.CharField(max_length=100)
    design=models.FileField()
    discription=models.TextField()
    DESIGNER=models.ForeignKey(designers_table, on_delete=models.CASCADE)


class materials_table(models.Model):
    material=models.CharField(max_length=200)
    photo=models.FileField()
    price=models.FloatField()
    stock=models.BigIntegerField()
    details=models.TextField()
    manufacture=models.ForeignKey(manufacturer_table, on_delete=models.CASCADE)


class orderinfo_table(models.Model):
    USER = models.ForeignKey(user_table, on_delete=models.CASCADE)
    date = models.DateField()
    amount = models.FloatField()
    status = models.CharField(max_length=100)
    DESIGNS = models.ForeignKey(designs_tables, on_delete=models.CASCADE)
    materials = models.ForeignKey(materials_table, on_delete=models.CASCADE)
    details = models.TextField()


class assign_table(models.Model):
    orderinfo=models.ForeignKey(orderinfo_table, on_delete=models.CASCADE)
    tailorshop=models.ForeignKey(tailorshop_table, on_delete=models.CASCADE)
    date=models.DateField()
    status=models.CharField(max_length=100)


class custom_desgin_table(models.Model):
    USER = models.ForeignKey(user_table, on_delete=models.CASCADE)
    date = models.DateField()
    status = models.CharField(max_length=100)
    DESIGNER = models.ForeignKey(designers_table, on_delete=models.CASCADE)
    design_details=models.TextField()


class chat(models.Model):
    fromid = models.ForeignKey(login_table, on_delete=models.CASCADE,related_name='fid')
    toid = models.ForeignKey(login_table, on_delete=models.CASCADE,related_name='tid')
    msg=models.TextField()
    date = models.DateField()


class MoreDesignTable(models.Model):
    DESIGN = models.ForeignKey(designers_table, on_delete=models.CASCADE)
    design_more = models.FileField()



