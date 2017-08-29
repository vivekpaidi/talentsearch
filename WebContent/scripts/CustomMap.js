var CustomMap = {
	
		'nameMap' : new Map(),
		'idTagMap': new Map(),
		'tagMap'  : new Map(),
		'countMap': new Map(),
		'mailMap': new Map(),
		'addName' : function(id,name){
			this.nameMap.set(id,name);
		},
		'addMail' : function(id,mail){
			this.mailMap.set(id,mail);
		},
		'addIdTag' : function(id,tags){
			this.idTagMap.set(id,tags);
		},
		'addTag'  : function(tag,id){
			var ids = this.tagMap.get(tag);
			if(ids == undefined)
				this.tagMap.set(tag,id);
			else
				this.tagMap.set(tag,ids+','+id);
		},
		'addCount' : function(id){
			var count = this.countMap.get(id);
			if(count == undefined)
				this.countMap.set(id,1);
			else
				this.countMap.set(id,count+1);
		},
		'decreaseCount' : function(id){
			var count = this.countMap.get(id);
			if(count == undefined)
				this.countMap.set(id,1);
			else{
				if(count-1 == 0){
					this.countMap.delete(id);
				}
				else{
				this.countMap.set(id,count-1);
				}
			}
				
		},
		'searchTag' : function(tag){
			var ids = String(this.tagMap.get(tag));
			if (this.tagMap.get(tag) == undefined){
				console.log("Hi undef");
			}
			else{
				var idArr = ids.split(",");
				for(i=0;i<idArr.length;i++){
					this.addCount(idArr[i]);
				}
			}
					
		},
		'removeSearchTag': function(tag){
			var ids = String(this.tagMap.get(tag));
			if (ids == undefined){
				return
			}
			else{
				var idArr = ids.split(",");
				for(i=0;i<idArr.length;i++){
					this.decreaseCount(idArr[i]);
				}
			}
		}
		
		
		
}